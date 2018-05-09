#ifndef __IMAGE_PNG_INCLUDED_
#define __IMAGE_PNG_INCLUDED_

// ------------------------------------------------------------------------------------------------
//
// ------------------------------------------------------------------------------------------------

//! Lee un fichero .png situado en la ruta especificada en el primer par�metro, y guarda informaci�n variada en las variables
//! pasadas como punteros.
//!
//! bpp: Bits por p�xel
//!
//! tamx: Tama�o X en p�xels
//! tamy: Tama�o Y en p�xels
//!
//! pitchx_bytes: Tama�o en bytes que tiene una l�nea de la imagen en memoria
//!
//! pitchx_pixels: Tama�o real en p�xels que tiene una l�nea de la imagen, necesario en m�quinas o tarjetas gr�ficas que no 
//!                soportan determinados tama�os
//!
//! pitchy_pixels: Tama�o real en p�xels que tiene una columna de la imagen, necesario en m�quinas o tarjetas gr�ficas que no 
//!                soportan determinados tama�os
//!
void *read_png_file(char *file_name, int *bpp, int *tamx, int *tamy, int *pitchx_bytes, int *pitchx_pixels, int *pitchy_pixels) ;

#endif
