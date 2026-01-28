Feature: Extração de Tabela Fipe de Motos

Scenario: 11 - CG 150 TITAN-KS/ TITAN-JOB
  Given Que consulto a moto com código "811070-0"
  When Realizo a requisição GET
  Then Valido o statuscode 200
  And Preencho o excel com os dados