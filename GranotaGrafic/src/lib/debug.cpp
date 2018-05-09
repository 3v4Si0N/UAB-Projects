#include <stdio.h>
#include <stdarg.h>

#include "debug.h"


// ------------------------------------------------------------------------------------------------
// Funci�n auxiliar para facilitar el uso de macros con par�metros variables
//
void log_msg(const char *format_string, ...)
{
  va_list args ;
  va_start(args, format_string) ;
  vprintf(format_string, args) ;
  va_end(args) ;
}
