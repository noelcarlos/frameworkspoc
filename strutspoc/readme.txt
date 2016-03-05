Redis
*****

Para descargar redis

https://github.com/MSOpenTech/redis/releases

Redis ya viene preconfigurado y solo hay que ejecutar el redis-server.exe en windows.

JMETER
******

Es necesario configurar JMeter para que acepte más de 5 redirecciones. 
Abrir el archivo jmeter.properties y añadir la linea siguiente

httpsampler.max_redirects=20