/***************************************************************************
 *            fwClient.h
 *
 *  Copyright  2016  mc
 *  <mcarmen@<host>>
 ****************************************************************************/
#include "fwClient.h"

/**
 * Function that sets the field addr->sin_addr.s_addr from a host name
 * address.
 * @param addr struct where to set the address.
 * @param host the host name to be converted
 * @return -1 if there has been a problem during the conversion process.
 */
int setaddrbyname(struct sockaddr_in *addr, char *host)
{
  struct addrinfo hints, *res;
	int status;

  memset(&hints, 0, sizeof(struct addrinfo));
  hints.ai_family = AF_INET;
  hints.ai_socktype = SOCK_STREAM;

  if ((status = getaddrinfo(host, NULL, &hints, &res)) != 0)
  {
    fprintf(stderr, "getaddrinfo: %s\n", gai_strerror(status));
    return -1;
  }

  addr->sin_addr.s_addr = ((struct sockaddr_in *)res->ai_addr)->sin_addr.s_addr;
  freeaddrinfo(res);

  return 0;
}


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
  int port = 0;

  optind=1;
  // We process the application execution parameters.
	while((param = getopt(argc, argv, "h:p:")) != -1)
  {
		switch((char) param)
    {
		  case 'h': break;
			case 'p':
			  // We modify the port variable just in case a port is passed as a
			  // parameter
				port = atoi(optarg);
				break;
			default:
				printf("Unknown parametre %c \n\n", (char) param);
				port = -1;
		}
	}

	return port;
}

/**
 * Returns the host name where the server is running.
 * @param argc the number of the application arguments.
 * @param an array with all the application arguments.
 * @Return Returns the host name where the server is running.<br />
 * Returns null if the application has been called with the wrong parameters.
 */
char * getHost(int argc, char* argv[])
{
  char * hostName = NULL;
  int param;

  optind=1;
    // We process the application execution parameters.
	while((param = getopt(argc, argv, "h:p:")) != -1)
  {
		switch((char) param)
    {
			case 'p': break;
			case 'h':
        hostName = (char*) malloc(sizeof(char)*strlen(optarg)+1);
				// Un cop creat l'espai, podem copiar la cadena
				strcpy(hostName, optarg);
				break;
			default:
				printf("Unknown parameter %c \n\n", (char) param);
				hostName = NULL;
		}
	}

	printf("in getHost host: %s\n", hostName); //!!!!!!!!!!!!!!
	return hostName;
}



/**
 * Shows the menu options.
 */
void print_menu()
{
		// Mostrem un menu perque l'usuari pugui triar quina opcio fer

		printf("\nAplicacion de gestion del firewall\n");
		printf("  0. Hello World\n");
		printf("  1. Listar las reglas filtradas\n");
		printf("  2. Insertar una regla de filtrado\n");
		printf("  3. Modificar una regla de filtrado\n");
		printf("  4. Eliminar una regla de filtrado\n");
		printf("  5. Eliminar todas las reglas de filtrado.\n");
		printf("  6. Exit\n\n");
		printf("Escoge una opcion: ");
}


/**
 * Sends a HELLO message and prints the server response.
 * @param sock socket used for the communication.
 */
void process_hello_operation(int sock)
{
  char buffer[MAX_BUFF_SIZE];
  memset(buffer, '\0', sizeof(buffer)); // Inicializamos el buffer a 0 todas las posiciones
  stshort(MSG_HELLO, &buffer); // op_code = 1, lo mandamos a buffer

  if(send(sock, &buffer, sizeof(buffer), 0) == -1)
    printf("Error sending op_code HELLO... \n");
  else
    printf("op_code MSG_HELLO has been sent... \n");

  recv(sock, &buffer, sizeof(buffer), 0);
  printf("\nReceived from server:\n--------------------\n%s\n--------------------\n", buffer+2);

}


void process_list_operation(int sock)
{
  char buffer[MAX_BUFF_SIZE];
  char *src_dst;
  char *src_dst_p;
  rule new_rule;
  int offset = 0;
  int i = 1;
  memset(buffer, '\0', sizeof(buffer)); // Inicializamos el buffer a 0 todas las posiciones

  stshort(MSG_LIST, &buffer); // op_code = 3, lo mandamos a buffer

  if(send(sock, &buffer, sizeof(buffer), 0) == -1)
    printf("Error sending op_code MSG_LIST... \n");
  else
    printf("op_code MSG_LIST has been sent... \n");

  recv(sock, &buffer, sizeof(buffer), 0);
  int num_rules = ldshort((buffer+2));
  printf("Hay %d reglas en la lista\n\n", num_rules);

  printf("Firewall FORWARD rules:\n--------------------\n");
  while(i <= num_rules)
  {
    new_rule = *((rule *)(buffer+4+offset)); //Recibimos del buffer la regla

    if(new_rule.src_dst_addr == 0)
      src_dst = "src";
    else
      src_dst = "dst";

    if(new_rule.src_dst_port == 0)
      src_dst_p = "sport";
    else if(new_rule.src_dst_port == 1)
      src_dst_p = "dport";
    else
      src_dst_p = "";

    if(new_rule.port == 0)
      printf("%d --> %s %s/%hu %s\n", i, src_dst, inet_ntoa(new_rule.addr), new_rule.mask, src_dst_p);
    else
      printf("%d --> %s %s/%hu %s %hu\n", i, src_dst, inet_ntoa(new_rule.addr), new_rule.mask, src_dst_p, new_rule.port);

    offset += sizeof(rule);
    i++;
  }
  printf("--------------------\n");
}


void process_add_operation(int sock)
{
  char buffer[MAX_BUFF_SIZE];
  rule new_rule;

  char src_dst[3];
  char ip[15];
  char src_dst_p[5];
  unsigned short srcDstAddr;
  unsigned short mascara;
  unsigned short srcDstP;
  unsigned short puerto;

  memset(buffer, '\0', sizeof(buffer)); // Inicializamos el buffer a 0 todas las posiciones
  stshort(MSG_ADD, &buffer); // op_code = 5, lo mandamos a buffer

  printf("Introduce la regla siguiendo el formato\n src|dst address Netmask [sport|dport] [port]:");
  scanf("%s %s %hu %s %hu", src_dst, ip, &mascara, src_dst_p, &puerto);

  if(strcmp(src_dst, "src") == 0)
    srcDstAddr = 0;
  else if(strcmp(src_dst, "dst") == 0)
    srcDstAddr = 1;

  new_rule.src_dst_addr = htons(srcDstAddr);
  inet_aton(ip, &new_rule.addr);
  new_rule.mask = htons(mascara);

  if(strcmp(src_dst_p, "sport") == 0)
    srcDstP = 0;
  else if(strcmp(src_dst_p, "dport") == 0)
    srcDstP = 1;
  else if(strcmp(src_dst_p, "0") == 0)
    srcDstP = 2;

  new_rule.src_dst_port = htons(srcDstP);
  new_rule.port = htons(puerto);

  *((rule*)(buffer+2)) = new_rule; // Insertamos la regla entera en el buffer

  
  if(send(sock, &buffer, sizeof(buffer), 0) == -1)
    printf("\nError sending op_code ADD + RULE... \n");
  else
    printf("\nop_code MSG_ADD + RULE has been sent... \n");
  fflush(stdin); //Eliminamos del buffer de entrada por teclado lo que habiamos escrito
}


void process_change_operation(int sock)
{
  char buffer[MAX_BUFF_SIZE];
  char src_dst[3];
  char ip[15];
  char src_dst_p[5];
  unsigned short srcDstAddr;
  unsigned short mascara;
  unsigned short srcDstP;
  unsigned short puerto;
  unsigned short rule_ID;
  unsigned short changeOK;
  rule new_rule;

  memset(buffer, '\0', sizeof(buffer)); // Inicializamos el buffer a 0 todas las posiciones
  stshort(MSG_CHANGE, &buffer); // op_code = 1, lo mandamos a buffer
  process_list_operation(sock);

  printf("Elige la regla a cambiar: ");
  scanf("%hu", &rule_ID);
  stshort(rule_ID, (buffer+2));

  printf("Introduce la regla siguiendo el formato\n src|dst address Netmask [sport|dport] [port]:");
  scanf("%s %s %hu %s %hu", src_dst, ip, &mascara, src_dst_p, &puerto);

  if(strcmp(src_dst, "src") == 0)
    srcDstAddr = 0;
  else if(strcmp(src_dst, "dst") == 0)
    srcDstAddr = 1;

  new_rule.src_dst_addr = htons(srcDstAddr);
  inet_aton(ip, &new_rule.addr);
  new_rule.mask = htons(mascara);

  if(strcmp(src_dst_p, "sport") == 0)
    srcDstP = 0;
  else if(strcmp(src_dst_p, "dport") == 0)
    srcDstP = 1;
  else if(strcmp(src_dst_p, "0") == 0)
    srcDstP = 2;

  new_rule.src_dst_port = htons(srcDstP);
  new_rule.port = htons(puerto);

  *((rule*)(buffer+4)) = new_rule; // Insertamos la regla entera en el buffer

  if(send(sock, &buffer, sizeof(buffer), 0) == -1)
    printf("\nError sending op_code MSG_CHANGE + rule... \n");
  else
    printf("\nop_code MSG_CHANGE + rule has been sent... \n");
  fflush(stdin); //Eliminamos del buffer de entrada por teclado lo que habiamos escrito

  recv(sock, &buffer, sizeof(buffer), 0);
  changeOK = ldshort(buffer);
  if(changeOK == MSG_OK)
    printf(OK_MSG "\n");
  else
    printf(NOK_MSG "\n");
}


void process_delete_operation(int sock)
{
  char buffer[MAX_BUFF_SIZE];
  unsigned short rule_ID;
  unsigned short deleteOK;
  memset(buffer, '\0', sizeof(buffer)); // Inicializamos el buffer a 0 todas las posiciones
  stshort(MSG_DELETE, &buffer); // op_code = 1, lo mandamos a buffer
  process_list_operation(sock);

  printf("Elige la regla a eliminar: ");
  scanf("%hu", &rule_ID);
  stshort(rule_ID, (buffer+2));

  if(send(sock, &buffer, sizeof(buffer), 0) == -1)
    printf("\nError sending op_code MSG_DELETE + rule_ID... \n");
  else
    printf("\nop_code MSG_DELETE + rule_ID has been sent... \n");

  recv(sock, &buffer, sizeof(buffer), 0);
  deleteOK = ldshort(buffer);
  if(deleteOK == MSG_OK)
    printf(OK_MSG "\n");
  else
    printf(NOK_MSG "\n");
}


void process_flush_operation(int sock)
{
  char buffer[MAX_BUFF_SIZE];
  unsigned short deleteOK;
  memset(buffer, '\0', sizeof(buffer)); // Inicializamos el buffer a 0 todas las posiciones
  stshort(MSG_FLUSH, &buffer); // op_code = 8, lo mandamos a buffer

  if(send(sock, &buffer, sizeof(buffer), 0) == -1)
    printf("Error sending op_code MSG_FLUSH... \n");
  else
    printf("op_code MSG_FLUSH has been sent... \n");

  recv(sock, &buffer, sizeof(buffer), 0);
  deleteOK = ldshort(buffer);
  if(deleteOK == MSG_OK)
    printf(OK_MSG "\n");
  else
    printf(NOK_MSG "\n");
}


/**
 * Closes the socket connected to the server and finishes the program.
 * @param sock socket used for the communication.
 */
void process_exit_operation(int sock)
{
  //TODO
  char buffer[MAX_BUFF_SIZE];
  memset(buffer, '\0', sizeof(buffer)); // Inicializamos el buffer a 0 todas las posiciones
  stshort(MSG_FINISH, &buffer);

  if(send(sock, &buffer, sizeof(buffer), 0) == -1)
    printf("Error sending op_code FINISH... \n");
  else
    printf("op_code MSG_FINISH has been sent... \n");

  close(sock);
  exit(0);
}

/**
 * Function that process the menu option set by the user by calling
 * the function related to the menu option.
 * @param s The communications socket
 * @param option the menu option specified by the user.
 */
void process_menu_option(int s, int option)
{
  switch(option)
  {
    // Opció HELLO
    case MENU_OP_HELLO:
      process_hello_operation(s);
      break;
    case MENU_OP_LIST_RULES:
      process_list_operation(s);
      break;
    case MENU_OP_ADD_RULE:
      process_add_operation(s);
      break;
    case MENU_OP_CHANGE_RULE:
      process_change_operation(s);
      break;
    case MENU_OP_DEL_RULE:
      process_delete_operation(s);
      break;
    case MENU_OP_FLUSH:
      process_flush_operation(s);
      break;
    case MENU_OP_EXIT:
      process_exit_operation(s);
    default:
      printf("Invalid menu option\n");
  }
}


int main(int argc, char *argv[])
{
  unsigned short port;
  char *hostName;
  int menu_option = 0;

  port = getPort(argc, argv);
  hostName = getHost(argc, argv);
  struct sockaddr_in server_addr;

  //Checking that the host name has been set.Otherwise the application is stopped.
	if((hostName == NULL) || port == 0)
  {
		perror("Not specified server name or port!!!!\n\n");
    printf("[HELP] ./fwClient -p [PORT] -h [SERVER NAME]\n");
		exit(1);
	}

  int sock = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP);
  if(sock == -1)
  {
    printf("Error creating socket...\n");
    exit(1);
  }
  printf("Client socket created\n");

  server_addr.sin_family = AF_INET;
  server_addr.sin_port = htons(port);
  setaddrbyname(&server_addr, hostName); // ponemos addr->sin_addr.s_addr con el hostName que ponga el usuario

  socklen_t size = sizeof(server_addr);

  if((connect(sock, (struct sockaddr *)&server_addr, size)) == -1)
    printf("Error connecting with server...\n");
  else
    printf("Client successfully connected to the server \n");

  do
  {
    print_menu();
    // getting the user input.
    scanf("%d",&menu_option);
    printf("\n\n");
    process_menu_option(sock, menu_option);

  }while(menu_option != MENU_OP_EXIT); //end while(opcio)

  return 0;
}
