#ifndef __MOUSE_INCLUDED_
#define __MOUSE_INCLUDED_


// ------------------------------------------------------------------------------------------------
//
// ------------------------------------------------------------------------------------------------

//! Enum de los botones del rat�n

enum {

  //! Constante de control para uso interno
  BUTTON_NONE,                    // BUTTON_NONE tiene que ser >= 0 o se machacar� memoria

  //! Bot�n principal del rat�n (izquierdo en un rat�n de 2 o 3 botones, o el �nico en un Mac)
  BUTTON_1,

  //! Bot�n principal del rat�n (izquierdo en un rat�n de 2 o 3 botones, o el �nico en un Mac)
  BUTTON_LEFT = BUTTON_1,

  //! Bot�n del medio en un rat�n de 3 botones
  BUTTON_2,

  //! Bot�n del medio en un rat�n de 3 botones
  BUTTON_MIDDLE = BUTTON_2,

  //! Bot�n derecho del rat�n
  BUTTON_3,

  //! Bot�n derecho del rat�n
  BUTTON_RIGHT = BUTTON_3,

  //! Constante de control para uso interno, indica a cu�ntos botones damos soporte
  MAX_NUM_POINTER_BUTTONS
} ;


// ------------------------------------------------------------------------------------------------
//
// ------------------------------------------------------------------------------------------------

//! \brief Estado del rat�n

//! Estructura de datos donde guardamos el estado del rat�n (posici�n, movimiento, botones, etc)

struct T_MOUSE
{
  // Posici�n actual del rat�n en la ventana
  int x, y ;

  // Posici�n del rat�n en la ventana en el frame anterior
  int last_x, last_y ;

  // Desplazamiento que ha hecho el rat�n desde el �ltimo frame
  int offset_x, offset_y ;

  // Array de n�mero de botones que indica si est�n pulsados (1) o no (0)
  int cnt[MAX_NUM_POINTER_BUTTONS] ;

  // Array de n�mero de botones que indica si han sido pulsados este frame (1) o no (0)
  int trg[MAX_NUM_POINTER_BUTTONS] ;

  // Indican si este frame se ha pulsado la rueda del rat�n. La rueda no se puede mantener
  // pulsada, as� que no necesitamos distinguirlos como en el caso de los botones
  int bWheelUp, bWheelDown, bWheelLeft, bWheelRight ;
} ;


// ------------------------------------------------------------------------------------------------
//
// ------------------------------------------------------------------------------------------------

//! Esta funci�n no est� pensada para usarse directamente. Avisa de que se acaba de soltar un bot�n del rat�n
void Mouse_ProcessButtonDown(int boton) ;

//! Esta funci�n no est� pensada para usarse directamente. Avisa de que se acaba de soltar un bot�n del rat�n
void Mouse_ProcessButtonUp(int boton) ;

//! Esta funci�n procesa los datos de haber movido la rueda del rat�n
void Mouse_ProcessWheel(int wheelx, int wheely) ;

//! Inicializa la estructura de datos de rat�n para poderla usar
void Mouse_Init() ;

//! Procesa los datos que tenemos del rat�n para que luego podamos leer algo con sentido
void Mouse_Tick() ;


// ------------------------------------------------------------------------------------------------
//
// ------------------------------------------------------------------------------------------------

//! Variable que guarda el estado del rat�n
extern struct T_MOUSE g_Mouse ;

#endif
