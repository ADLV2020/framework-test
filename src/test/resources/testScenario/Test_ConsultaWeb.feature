
@Busquedas
Feature: Realizar b�squedas de contenido y analizar el texto.
	As un usuario de p�ginas de busqueda
  I want buscar un articulo seg�n unas palabaras claves
  So That analizar el texto en busqueda de datos

  @BuscarSimple
  Scenario: Dado una palabra a buscar, analizar el texto para obtener una fecha
    Given accedo al buscador de Google
    And busco por la palabra "automatizacion" en Google
    When selecciono el resultado para "Wikipedia" en la lista
    And analizo los parrafos para encontrar "primer proceso" en la oraci�n 
    Then obtengo el a�o del acontecimiento del hecho

