CC = gcc
CFLAGS = -Wall

all: rConfApp
	
rConfApp: 
	$(CC) $(CFLAGS) fwServer.c common.c -o fwServer
	$(CC) $(CFLAGS) fwClient.c common.c -o fwClient
			
clean:
	rm fwServer fwClient

					
					
