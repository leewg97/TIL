## Transaction
데이터베이스의 상태를 변화시키는 하나의 논리적 기능을 수행하기 위한 작업의 단위. <br>
한꺼번에 모두 수행되어야 하는 일련의 과정들.  <br>

**데이터베이스의 상태를 변화시킨다는 것은 무엇일까?** <br>
간단하게 말해 `SELECT`, `INSERT`, `UPDATE`, `DELETE`의 질의어(SQL)를 이용하여 데이터베이스에 접근하는 것을 의미.

**여기서 작업의 단위는?**  <br>
많은 질의어 명령문들을 사람이 정하는 기준에 따라 정하는 것을 의미. <br>
착각하지 말아야 할 것은 작업의 단위는 질의어 한 문장이 아니라는 것.

## Characteristics of Transaction
트랜잭션의 특징은 크게 4가지로 구분.
* 원자성(Atomicity)
* 일관성(Consistency)
* 독립성, 격리성(Isolation) 
* 지속성, 영속성(Durability)

`원자성` : 한 트랜잭션 내에서 실행한 작업들은 하나로 간주한다. 즉, 모두 성공 또는 모두 실패. <br>
`일관성` : 트랜잭션이 진행되는 동안에 데이터베이스가 변경 되더라도 처음 트랜잭션을 진행 하기 위해 참조한 데이터베이스로 진행된다.<br>
`독립성` : 동시에 실행되는 트랜잭션들이 서로 영향을 미치지 않도록 격리해야한다.<br>
`지속성` : 트랜잭션을 성공적으로 마치면 결과가 항상 저장되어야 한다.<br>

## Commit, Rollback
`Commit`이란 하나의 트랜잭션이 성공적으로 끝났고, 데이터베이스가 일관성있는 상태에 있을 때, 하나의 트랜잭션이 끝났다라는 것을 알려주기위해 사용하는 연산이다. 이 연산을 사용하면 수행했던 트랜잭션이 로그에 저장되며, 후에 Rollback 연산을 수행했었던 트랜잭션단위로 하는것을 도와준다.<br>

`Rollback`이란 하나의 트랜잭션 처리가 비정상적으로 종료되어 트랜잭션의 원자성이 깨진경우, 트랜잭션을 처음부터 다시 시작하거나, 트랜잭션의 부분적으로만 연산된 결과를 다시 취소시킨다. 후에 사용자가 트랜잭션 처리된 단위대로 Rollback을 진행할 수도 있다.