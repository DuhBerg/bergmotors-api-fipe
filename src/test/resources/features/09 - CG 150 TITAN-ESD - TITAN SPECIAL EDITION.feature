Feature: Extração de Tabela Fipe de Motos

Scenario: 09 - CG 150 TITAN-ESD - TITAN SPECIAL EDITION
  Given Que consulto a moto com código "811072-7"
  When Realizo a requisição GET
  Then Valido o statuscode 200
  And Preencho o excel com os dados