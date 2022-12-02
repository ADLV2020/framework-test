@Busquedas
Feature: Realizar b�squedas de contenido y analizar el texto.
	As un usuario de p�ginas de busqueda
  I want buscar un articulo seg�n unas palabaras claves
  So That analizar el texto en busqueda de datos

  @BuscarSceOutline @All
  Scenario Outline: Dado una palabra a buscar, analizar el texto para obtener una fecha
    Given accedo al buscador de Google
    And busco por la palabra <keywordToSearch> en Google
    When selecciono el resultado para <pageToConsult> en la lista
    And analizo los parrafos para encontrar <textToFind> en la oraci�n 
    Then obtengo el a�o del acontecimiento del hecho
    
    Examples:
          | keywordToSearch        | pageToConsult        | textToFind                     |
          | "automatizacion"       | "Wikipedia"          | "primer proceso"               |
