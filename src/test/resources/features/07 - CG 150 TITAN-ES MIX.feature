Feature: Extração de Tabela Fipe de Motos

Scenario: 07 - CG 150 TITAN-ES MIX
  Given Que consulto a moto com código "811092-1"
  When Realizo a requisição GET
  Then Valido o statuscode 200
  And Preencho o excel com os dados