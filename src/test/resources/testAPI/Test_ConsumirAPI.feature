Feature: Consumir servicios de PetStore
	
	As un usuario de los microservicios del PetStore
  I want consumir los diferentes endpoint disponibles
  So realizar altas y gestionar consultas 
	
  @API @AltaUser @All
  Scenario Outline: Realizar el alta de un nuevo usuario en PetStore
		Given tengo a la <URL> del servicio
		And tengo el <ENDPOINT> de la api
		When ingreso los datos de <USERNAME>, <FIRSTNAME>, <LASTNAME>, <EMAIL>, <PASSWORD>, <PHONE> del body
		Then obtengo el codigo <CODIGO> de respuesta
    Examples: 
    |	URL 	 														|	ENDPOINT 							|	USERNAME					|	FIRSTNAME		 |	LASTNAME  	|	EMAIL  												|	PASSWORD  	|	PHONE 					|	CODIGO 	|
    |"https://petstore.swagger.io/v2"		|"/user"								|"Anibal-DeLaVega"	|"Anibal"			 |"De La Vega" 	|"hannibal.delavega@gmail.com" 	|"Lito1234"	 	|"5491164235548"	|200	 		|
    
    
  @API @ConsultaUser @All
  Scenario Outline: Realizar la consulta por un usuario dado de alta en PetStore
		Given tengo a la <URL> del servicio
		And tengo el <ENDPOINT> de la api
		When ingreso el <USERNAME> del usuario a consultar
		Then obtengo el <ID> de respuesta
    Examples: 
    |	URL 	 														|	ENDPOINT 							|	USERNAME					|	ID		 									|
    |"https://petstore.swagger.io/v2"		|"/user"								|"Anibal-DeLaVega"	|1961199612443397922			|
    
    
  @API @ConsultaPetStatus @IdName @All
  Scenario Outline: Realizar la consulta por un usuario dado de alta en PetStore
		Given tengo a la <URL> del servicio
		And tengo el <ENDPOINT> de la api
		When informo el <STATUS> de las mascotas
		Then obtengo el una lista de mascotas y sus ids
    Examples: 
    |	URL 	 														|	ENDPOINT 							|	STATUS					|
    |"https://petstore.swagger.io/v2"		|"/pet/findByStatus?"		|"sold"						|
    
    
  @API @ConsultaPetStatus @CountName @All
  Scenario Outline: Realizar la consulta por un usuario dado de alta en PetStore
		Given tengo a la <URL> del servicio
		And tengo el <ENDPOINT> de la api
		When informo el <STATUS> de las mascotas
		Then obtengo la cantidad de veces que se repite un nombre
    Examples: 
    |	URL 	 														|	ENDPOINT 							|	STATUS					|
    |"https://petstore.swagger.io/v2"		|"/pet/findByStatus?"		|"sold"						|
    
    