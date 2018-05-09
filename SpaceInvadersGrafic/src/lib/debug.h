#ifndef __DEBUG_INCLUDED_
#define __DEBUG_INCLUDED_

#ifdef _WIN32
  #include <crtdbg.h>
#endif

// ------------------------------------------------------------------------------------------------
//
// ------------------------------------------------------------------------------------------------

#ifdef _WIN32
  #define ASSERT      _ASSERT
#endif

//! Imprime un mensaje de texto en la consola de debug
#define LOG(...)          { log_msg(__VA_ARGS__) ; }


// ------------------------------------------------------------------------------------------------
//
// ------------------------------------------------------------------------------------------------

//! Funci�n auxiliar para facilitar el uso de macros con par�metros variables
void log_msg(const char *format_string, ...) ;

#endif
