@regression
  Feature: Login
    Scenario: Acceso a login
      Given el usuario accede a la plataforma
      When agrega usuario correcto
      And agrega contraseña correcta
      Then inicia sesion a la plataforma