# -Capacitação JAVA

## 📋 Descrição
Alo desenvolvedor!

Este projeto foi executado como um desafio durante minha jornada na Internal Talent da Avanade. Como nunca tinha feito até então nada em Java tive que correr com meus estudos em paralelo desenvolvendo este Demo de RPG no console.




## 🖥️ Tecnologias

- Linguagem Java versão 17
- Spring Boot 3.0.6 usando Maven


## 🎨 Projetos
*✓ Falta Muito para estar Finalizado*

- Criação de um jogo RPG em Java.

## 🖼️ Informações do Jogo

Junte-se à batalha épica estilo Advanced Dangeous & Dragons (AD&D) !! 
Nos ajude a criar a melhor API Rest usando Java SpringBoot com banco de dados MySQL.
O jogo, como todo bom RPG (Role Playing Game), será duelado em turnos. 
Escolha o seu nome e personagem favorito (herói ou monstro). 
O seu oponente sempre será um monstro, você pode escolher ou deixar aleatório.
Sim, também teremos que “jogar” dados.
Algumas regras:
Ao iniciar, será necessário escolher um personagem (herói ou monstro);
Cada personagem tem seus atributos únicos. Escolha com calma o seu personagem;
Os personagens possuem Pontos de Vida (PV);
Se um personagem ficar com PV igual ou abaixo de zero então o oponente será o vencedor;
O dano causado por um ataque depende da força do atacante e da defesa do defensor, enquanto o dano
recebido depende da força do atacante e da resistência do defensor e da eficácia de sua defesa;
Banco de Dados Postgres;
Necessário criar o CRUD (Create, Read, Update e Delete) de cadastro de Personagem;

## Dados
Os dados em jogos de RPG podem ter diferentes números de faces, indicado pelo número após a letra "d". Por
exemplo, 1d12 indica que você deve jogar um dado de 12 faces, ou seja, o resultado será um número aleatório entre
1 e 12.
Quando a notação inclui mais de um dado, o número antes do "d" indica quantos dados devem ser jogados. Por
exemplo, 2d8 significa que você deve jogar dois dados de 8 faces e somar os resultados.
Portanto, para jogar 2d20, você deve jogar dois dados de 20 faces e somar os resultados. O resultado final será um
número aleatório entre 2 e 40.

## Fluxo do Jogo

## Iniciativa
Precisamos definir quem vai começar o jogo atacando ou como chamamos no RPG, quem terá a iniciativa.
Para isso, jogue um dado de 20 faces (1d20 → número possível de 1 a 20).
Não temos empates e quem tirar o maior valor terá a iniciativa.

## Turno
O turno é dividido em 2 partes. Ataque e defesa.
2.1) Ataque
O ataque é bem simples. Precisará jogar um dado de 12 faces (1d12 → número possível de 1 a 12) somar com a Força e com a Agilidade.
2.2) Defesa
A defesa é calculada também jogando um dado de 12 faces (1d12 → número possível de 1 a 12) somar com a Defesa e com a Agilidade.
Se o valor do ataque for maior do que a defesa, então o dano será calculado (próximo tópico).
Se o valor do ataque for menor ou igual ao valor da defesa, então o defensor conseguiu realizar a defesa e não receberá nenhum dano..

## Dano
Se a defesa foi menor do que o ataque então será necessário calcular o dano.
O cálculo é bem simples.
Jogue o(s) dado(s) de acordo com o Dano que o personagem possui e some o valor da Força do personagem.

exemplo:

Bárbaro → quantidade de dados x faces do dado, ou seja, 2 números aleatórios que variam de 1 a 8 onde a soma será no mínimo 2 e no máximo 16.

Orc → quantidade de dados x faces do dado, ou seja, 3 números aleatórios que variam de 1 a 4 onde a soma será no mínimo 2 e no máximo 8.

## Pontos de Vida
Por fim, temos os pontos de vida do personagem.
Ao sofre o dano, devemos subtrair o valor do dano dos PV do personagem.
O personagem que ficar com zero ou menos de PV então a luta terminará instantaneamente.

## Fim do Turno
Se no fim do turno nenhum personagem ficou com zero ou menos PV então a luta continua e o próximo turno se inicia imediatamente.

## Historico
Todos os detalhes das batalhas deverão ser salvas em tabela de LOG para futura conferência.
Dados:
qual heroi, qual monstro, quem iniciou a batalha, dados de cada turno (número do turno, dado de ataque, defesa,dano, etc)



