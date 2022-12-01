
@Busquedas
Feature: Realizar búsquedas de contenido y analizar el texto.
	As un usuario de páginas de busqueda
  I want buscar un articulo según unas palabaras claves
  So That analizar el texto en busqueda de datos

  @BuscarSimple
  Scenario: Dado una palabra a buscar, analizar el texto para obtener una fecha
    Given accedo al buscador de Google
    And busco por la palabra "automatizacion" en Google
    When selecciono el resultado para "Wikipedia" en la lista
    And analizo los parrafos para encontrar "primer proceso" en la oración 
    Then obtengo el año del acontecimiento del hecho

