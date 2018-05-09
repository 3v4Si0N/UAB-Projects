/***************************************************************************
 *            fwServer.c
 *
 *  Copyright  2016  mc
 *  <mc@<host>>
 ****************************************************************************/

#include "fwServer.h"

/**
 * Returns the port specified as an application parameter or the default port
 * if no port has been specified.
 * @param argc the number of the application arguments.
 * @param an array with all the application arguments.
 * @return  the port number from the command line or the default port if
 * no port has been specified in the command line. Returns -1 if the application
 * has been called with the wrong parameters.
 */
int getPort(int argc, char* argv[])
{
  int param;
  int port = DEFAULT_PORT;

  optind=1;
  // We process the application execution parameters.
  while((param = getopt(argc, argv, "p:")) != -1)
  {
    switch((char) param)
    {
      case 'p':
      // We modify the port variable just in case a port is passed as a
      // parameter
      port = atoi(optarg);
      break;
      default:
      printf("Parametre %c desconegut\n\n", (char) param);
      port = -1;
    }
  }
  return port;
}


 /**
 * Function that sends a HELLO_RP to the  client
 * @param sock the communications socket
 */
void process_HELLO_msg(int sock)
{
  char buffer[MAX_BUFF_SIZE];
  memset(buffer, '\0', sizeof(buffer)); // Inicializamos el buffer a 0 todas las posiciones

  stshort(MSG_HELLO_RP, &buffer); // Enviamos op_code = 2 HELLO_RP por buffer
  strcpy(buffer+2, "Hello World"); // Copiamos HELLOWORLD en buffer + 2 bytes del opcode
  
  if(send(sock, &buffer, sizeof(buffer), 0) == -1) // Enviamos el mensaje de 14 bytes
    printf("[!] Error cannot send Hello World to client... \n");
  else
    printf("  [>] Hello World has been sent to client\n");
}


void InicializarLista(struct FORWARD_chain *lista)
{
  lista->first_rule = NULL;
  lista->last_rule = NULL;
  lista->num_rules = 0;
}


void process_LIST_msg(int sock, struct FORWARD_chain *lista)
{
  char buffer[MAX_BUFF_SIZE];
  int offset=0;
  memset(buffer, '\0', sizeof(buffer)); // Inicializamos el buffer a 0 todas las posiciones
  
  stshort(MSG_RULES, &buffer);
  stshort(lista->num_rules, (buffer+2));

  struct fw_rule *actual;
  actual = lista->first_rule;

  while(actual != NULL)
  {
    *((rule*)(buffer+4+offset)) = actual->rule;
    offset += sizeof(rule);

    actual = actual->next_rule;
  }

  if(send(sock, &buffer, sizeof(buffer), 0) == -1) // Enviamos el mensaje de 14 bytes
    printf("[!] Error cannot send rule to client... \n");
  else
    printf("  [>] List rule has been sent to client\n");
}


int insertRule(struct FORWARD_chain *lista, unsigned short src_dstAddr, char *ip, unsigned short mascara, unsigned short src_dstPort, unsigned short Port)
{
  struct fw_rule *new_rule;

  /* reservamos memoria para el nuevo elemento */
  new_rule = (struct fw_rule *) malloc (sizeof(struct fw_rule));
  if(new_rule == NULL) printf( "No hay memoria disponible!\n");

  // Inicializamos los parametros de la regla a insertar
  new_rule->rule.src_dst_addr = src_dstAddr;
  inet_aton(ip, &new_rule->rule.addr);
  new_rule->rule.mask = mascara;
  new_rule->rule.src_dst_port = src_dstPort;
  new_rule->rule.port = Port;
 

  new_rule->next_rule = NULL;

  if(lista->first_rule == NULL)
  {
    lista->first_rule = new_rule;
    lista->last_rule = new_rule;
    printf("Se añade al principio (lista vacia)\n");
  }

  else
  {
    lista->last_rule->next_rule = new_rule;
    lista->last_rule = new_rule;
    
    printf("Se añade al final de la lista\n");
  }
  lista->num_rules++;
  return lista->num_rules;
}


/* Eliminar una regla después de la posición solicitada */
int deleteRule(struct FORWARD_chain *lista, unsigned short rule_ID)
{
  int i = 1;
  int pos = (int)rule_ID;
  struct fw_rule *actual;
  struct fw_rule *delete_rule;

  if ((lista->num_rules < 1) || (pos < 1) || (pos > lista->num_rules))
    return MSG_ERR;

  actual = lista->first_rule;
  while(i+1 < pos)
  {
    actual = actual->next_rule;
    i++;
  }

  if(actual == lista->first_rule && (pos == 1))
  {
    delete_rule = lista->first_rule;
    lista->first_rule = lista->first_rule->next_rule;
    actual = lista->first_rule;
  }
  else if(actual->next_rule == lista->last_rule)
  {
    delete_rule = actual->next_rule;
    lista->last_rule = actual;
    lista->last_rule->next_rule = NULL;
  }
  else
  {
    delete_rule = actual->next_rule;
    actual->next_rule = actual->next_rule->next_rule;
  }
  
  free(delete_rule);
  lista->num_rules--;
  printf("NUMERO DE REGLAS EN LA LISTA: %d\n", lista->num_rules);
  return MSG_OK;
}


int changeRule(struct FORWARD_chain *lista, unsigned short src_dstAddr, char *ip, unsigned short mascara, unsigned short src_dstPort, unsigned short Port, unsigned short rule_ID)
{
  int i = 1;
  int pos = (int)rule_ID;
  struct fw_rule *actual;
  struct fw_rule *change_rule;

  /* reservamos memoria para el nuevo elemento */
  change_rule = (struct fw_rule *) malloc (sizeof(struct fw_rule));
  if(change_rule == NULL) printf( "No hay memoria disponible!\n");

  if ((lista->num_rules < 1) || (pos < 1) || (pos > lista->num_rules))
    return MSG_ERR;

  actual = lista->first_rule;
  while(i+1 < pos)
  {
    actual = actual->next_rule;
    i++;
  }


  // Inicializamos los parametros de la regla a insertar
  change_rule->rule.src_dst_addr = src_dstAddr;
  inet_aton(ip, &change_rule->rule.addr);
  change_rule->rule.mask = mascara;
  change_rule->rule.src_dst_port = src_dstPort;
  change_rule->rule.port = Port;


  if(actual == lista->first_rule && (pos == 1))
  {
    lista->first_rule = change_rule;
    change_rule->next_rule = actual;
    lista->num_rules++;
    deleteRule(lista, rule_ID+1);
  }
  else if(actual == lista->last_rule)
  {
    deleteRule(lista, rule_ID);
    lista->last_rule->next_rule = change_rule;
    lista->last_rule = change_rule;
    lista->last_rule->next_rule = NULL;
    lista->num_rules++;
  }
  else
  {
    change_rule->next_rule = actual->next_rule;
    actual->next_rule = change_rule;
    lista->num_rules++;
    deleteRule(lista, rule_ID+1);
  }

  return MSG_OK;
}


int deleteAllRules(struct FORWARD_chain *lista)
{
  struct fw_rule *actual;
  actual = lista->first_rule;

  if(lista->first_rule == NULL)
    return MSG_ERR;

  while(lista->first_rule != NULL)
  {
    actual = lista->first_rule;
    lista->first_rule = lista->first_rule->next_rule;
    free(actual);
    lista->num_rules--;
  }
  return MSG_OK;
}


int process_FINISH_client(int sock)
{
  int finish = 1;

  printf("[-] Client has been disconnected \n");
  close(sock);
  return finish;
}


 /**
 * Receives and process the request from a client.
 * @param the socket connected to the client.
 * @param chain the chain with the filter rules.
 * @return 1 if the user has exit the client application therefore the
 * connection whith the client has to be closed. 0 if the user is still
 * interacting with the client application.
 */
int process_msg(int sock, struct FORWARD_chain *chain)
{
  unsigned short op_code;
  rule new_rule;

  int finish = 0;
  unsigned short changeOK;
  unsigned short deleteOK;

  char buffer[MAX_BUFF_SIZE];
  memset(buffer, '\0', sizeof(buffer)); // Inicializamos el buffer a 0 todas las posiciones

  recv(sock, &buffer, sizeof(buffer), 0); // Recibimos el opcode por el buffer
  op_code = ldshort(buffer); // Transformamos el opcode con ldshort


  switch(op_code)
  {
    case MSG_HELLO:
      process_HELLO_msg(sock);
      break;

    case MSG_LIST:
      process_LIST_msg(sock, chain);
      break;

    case MSG_ADD:
      new_rule = *((rule *)(buffer+2)); //Recibimos del buffer la regla
      insertRule(chain, ntohs(new_rule.src_dst_addr), inet_ntoa(new_rule.addr), ntohs(new_rule.mask), ntohs(new_rule.src_dst_port), ntohs(new_rule.port));
      break;

    case MSG_CHANGE:
      new_rule = *((rule *)(buffer+4)); //Recibimos del buffer la regla
      changeOK = changeRule(chain, ntohs(new_rule.src_dst_addr), inet_ntoa(new_rule.addr), ntohs(new_rule.mask), ntohs(new_rule.src_dst_port), ntohs(new_rule.port), ldshort((buffer+2)));
      stshort(changeOK, &buffer);
      send(sock, &buffer, sizeof(buffer), 0);
      break;

    case MSG_DELETE:
      deleteOK = deleteRule(chain, ldshort((buffer+2)));
      stshort(deleteOK, &buffer);
      send(sock, &buffer, sizeof(buffer), 0);
      break;

    case MSG_FLUSH:
      deleteOK = deleteAllRules(chain);
      stshort(deleteOK, &buffer);
      send(sock, &buffer, sizeof(buffer), 0);
      break;

    case MSG_FINISH:
      finish = process_FINISH_client(sock);
      break;
      
    default:
      perror("Message code does not exist.\n");
  }
  return finish;
}

int main(int argc, char *argv[])
{
  int port = getPort(argc, argv);
  int finish = 0, fd[2], status;
  struct FORWARD_chain chain;
  pid_t pid;

  InicializarLista(&chain);

  insertRule(&chain, 0, "192.168.1.12", 32, 1, 80);
  insertRule(&chain, 1, "129.168.1.33", 32, 0, 81);
  insertRule(&chain, 0, "192.168.1.22", 32, 1, 82);
  insertRule(&chain, 1, "129.168.1.55", 32, 0, 83);
  insertRule(&chain, 0, "192.168.1.66", 32, 1, 84);
  insertRule(&chain, 1, "129.168.1.88", 32, 0, 85);


  int serverSocket = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP);
  if (serverSocket == -1)
  {
    printf("[!] Error creating socket...\n");
    exit(1);
  }
  printf("Server socket connection created...\n");

  struct sockaddr_in server_addr;
  server_addr.sin_family = AF_INET;
  server_addr.sin_addr.s_addr = INADDR_ANY;
  server_addr.sin_port = htons(port);

  if (bind(serverSocket, (struct sockaddr*)&server_addr, sizeof(server_addr)) == -1)
  {
    printf("[!] Error binding socket...\n");
    exit(1);
  }

  struct sockaddr_in client_addr;
  socklen_t size = sizeof(client_addr);

  if(listen(serverSocket, MAX_QUEUED_CON) == -1)
    printf("[!] Error while listenning...\n");

  printf("Server is running on port: %d \n", port);
  printf("Waiting for clients...\n");

  while(1)
  {
    //TODO
    int client = accept(serverSocket, (struct sockaddr*)&client_addr,&size);
    if (client == -1)
    {
      printf("Error accepting connection...\n");
      exit(EXIT_FAILURE);
    }

    if(pipe(fd) < 0) // creamos los pipes y si ha habido algún error al crear alguno, lo notificamos
    {
      perror("Error al crear el pipe %d\n"); // notificamos que ha habido algún error a la hora de crear algún pipe
      exit(EXIT_FAILURE); // acaba mal
    }


    pid = fork();
    if(pid == 0) // Somos un proceso hijo
    {
      printf("\n[+] New client has been connected... \n");
      do
      {
        //TODO: finish = process_msg(....., &chain);
        finish = process_msg(client, &chain);
        write(fd[WRITE], &chain, sizeof(struct FORWARD_chain));

      }while(!finish);
      //TODO
      //exit(EXIT_SUCCESS);
      close(client);
    }
    else
    {
      if(pid == -1)
      {
        perror("Error in fork");
        exit(EXIT_FAILURE);
      }
      read(fd[READ], &chain, sizeof(struct FORWARD_chain));
      wait(&status);
    }
  }
  return 0;
}