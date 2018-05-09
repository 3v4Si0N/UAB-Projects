#ifndef __SOUND_INCLUDED_
#define __SOUND_INCLUDED_

#include "stb_vorbis.h"

// ------------------------------------------------------------------------------------------------
//
// ------------------------------------------------------------------------------------------------


//! N�mero m�ximo de sonidos sonando de manera simult�nea

#define MAX_SIMULTANEOUS_SOUND_CHANNELS                                  8

//! El n�mero m�ximo de canales reservados a efectos de sonido es el total menos el de la m�sica y el del ambiente

#define MAX_SIMULTANEOUS_SOUND_CHANNELS_FOR_SOUND_EFFECTS                (MAX_SIMULTANEOUS_SOUND_CHANNELS - 1 - 1)


//! Tipos de streaming

enum {

  //! Reproducir desde un buffer de memoria (Ideal para sonidos peque�os)
  PLAY_FROM_MEMORY,

  //! Reproducir cargando poco a poco desde disco (Ideal / Necesario para sonidos grandes)
  PLAY_FROM_DISK_STREAM
} ;


//! Tipos de loop

enum {

  //! Cuando le damos al play el sonido suena desde el principio si estaba parado, o contin�a por donde iba si estaba en pause
  SOUND_PLAY_NORMAL,

  //! Si el sonido ya estaba reproduci�ndose lo deja continuar en vez de hacer que vuelva al principio
  SOUND_DO_NOT_RESTART_IF_ALREADY_PLAYING,

  //! El sonido vuelve a comenzar desde el principio INCLUSO si s�lo estaba pausado
  SOUND_FORCE_RESTART,
} ;




//! Tipos de loop

enum {

  //! El sonido se detiene al llegar al final
  PLAY_THEN_STOP_AT_END,

  //! El sonido vuelve a comenzar cuando acaba
  PLAY_THEN_LOOP_AT_END
} ;


//! Estados en los que puede estar un sonido

enum {

  //! El sonido est� parado, y cuando comience a reproducirse de nuevo lo har� desde el principio
  SOUND_STATE_STOPPED,

  //! El sonido est� pausado, y cuando comience a reproducirse de nuevo lo har� desde donde se qued� al pausarlo
  SOUND_STATE_PAUSED,

  //! El sonido est� reproduci�ndose
  SOUND_STATE_PLAYING
} ;


// ------------------------------------------------------------------------------------------------
//
// ------------------------------------------------------------------------------------------------

struct T_SOUND
{
  //! Longitud en samples del sonido
  int num_samples ;

  //! Posici�n en la que se encuentra del stream
  int posicion_stream ;
	
  //! Indica el modo de loop del sonido
  int bLoop ;

  //! Indica en qu� estado se encuentra el sonido
  int estado ;

  //! Puntero al buffer de almacenamiento si se reproduce desde memoria, NULL si se reproduce desde disco
  unsigned char *buffer_fichero ;

  //! Posici�n que est� ocupando en la cola, en caso de estar playe�ndose
  int posicion_cola ;

  //! Puntero a una estructura de datos de la librer�a stb_vorbis
  stb_vorbis *ogg ;
} ;


// ------------------------------------------------------------------------------------------------
//
// ------------------------------------------------------------------------------------------------

//! Inicializa la librer�a de sonido
void Sound_Init() ;

//! Libera los recursos alojados por la librer�a de sonido
void Sound_Release() ;

//struct T_SOUND *Sound_LoadFileStream(char *path, int bLoop) ;
//struct T_SOUND *Sound_LoadMemoryStream(char *path, int bLoop) ;

//! Funci�n de alto nivel que automatiza la carga y reproducci�n de la m�sica de fondo
struct T_SOUND *Sound_LoadMusic(char *path, int bStream) ;

//! Carga un sonido y lo deja listo para comenzar a usarlo
struct T_SOUND *Sound_LoadSound(char *path) ;

//! Comienza la reproducci�n del sonido pasado como par�metro. Devuelve el n�mero de slot que emplear� el sonido para reproducirse, o NO_SOUND_CHANNEL_AVAILABLE si no queda ninguno libre
int Sound_Play(struct T_SOUND *sonido, int mode) ;

//! Pausa la reproducci�n del sonido pasado como par�metro
void Sound_Pause(struct T_SOUND *sonido) ;

//! Detiene la reproducci�n del sonido pasado como par�metro
void Sound_Stop(struct T_SOUND *sonido) ;

//! Reinicia la reproducci�n del sonido pasado como par�metro
void Sound_Restart(struct T_SOUND *sonido) ;

//! Borra el sonido pasado como par�metro y libera los recursos que haya podido alojar
void Sound_Delete(struct T_SOUND *sonido) ;

#endif