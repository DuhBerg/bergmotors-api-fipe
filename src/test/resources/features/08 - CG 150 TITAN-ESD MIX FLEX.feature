Feature: Extração de Tabela Fipe de Motos

Scenario: 08 - CG 150 TITAN-ESD MIX/FLEX
  Given Que consulto a moto com código "811093-0"
  When Realizo a requisição GET
  Then Valido o statuscode 200
  And Preencho o excel com os dados