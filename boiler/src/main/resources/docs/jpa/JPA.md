
### N + 1

* 조회한 데이터 개수만큼 연관관계에 있는 테이블을 조회하는 현상 쿼리를 한번 요청해도 N번만큼의 쿼리가 추가적으로 발생
* JPQL이 연관관계에 있는 테이블을 조회하지 않기 때문에 발생
* default_batch_fetch_size,fetchJoin중 상황에 맞게 사용 
  * default_batch_fetch_size는 fetchJoin으로 인하여 발생하는 문제점은 없지만 사이즈를 수동으로 변경해줘야함 
  * fetchJoin는 한번의 쿼리로 연관된 엔티티를 모두 조회하지만, fetchJoin으로 구현할 수 없는 상황존재(on조건)

------

### default_batch_fetch_size

* 연관관계에있는 엔티티를 한꺼번에 읽어올 수 있는 개수를 지정
* N + 1상황에서 조회된 데이터 개수만큼 연관관계 테이블을 조회하는 쿼리를 전송

------

### fetchJoin
- fetchJoin과 일반join비교
  - fetchJoin은 N + 1 문제를 해결하기 위해 연관관계에있는 테이블까지 한꺼번에 조회
    - querydsl에서 fetchJoin은 join방식을 규정하지 않음, left join,right join,inner join등 모든 join에서 fetchJoin사용가능
    - spring-data-jpa에서 @EntityGraph의 fetchJoin은 left join만 사용함
  - fetchJoin은 테이블의 모든 컬럼을 읽어오기때문에 상황에 따라 비효율적(안쓰는 컬럼이 응답시간에 영향을 줄 수 있음)
  - 일반join은 join쿼리가 발생하지만 select절에서 연관관계테이블의 데이터는 조회하지 않음
  - 모든칼럼이 필요하지 않다면 상황에 따라 일반join을쓴다음 dto로 조회하는 방법이 효율적
  - fetchJoin은 하나의 메소드만 정의해두면, 필요에 따라 여러dto를 만들 수 있음
  - 일반join을 이용해서 dto로 조회한다면 dto가 화면에 의존하기때문에 요구사항에 따라 매번 조회메소드를 추가해야될 수 있음<br/>

  
- fetchJoin에서 제한사항
  - on을 사용할 수 없음, on을 사용하여 연관관계에 대상에 조건을 걸면 db상태와 객체의 일관성이 깨질 수 있음
    - fetchJoin은 연관관계에 있는 엔티티가 모두 조회될걸 가정
  - fetchJoin에서 where은 사용할 수 있지만 상황에 따라 일관성이 깨질 가능성 존재()
    - outer join에서 where을 쓴다면 일관성을 보장할 수 없음
    - fetchJoin에서 outer join은 왼쪽에 테이블의 모든 row가 보여지는걸 가정하기 때문에 where조건을 거는 순간 필터링으로 작용
      - on에서 쓰는 조건과 where에서 쓰는 조건이 다르게 동작
    - inner join에서 대상쪽에 where을 쓴다면 일관성을 보장할 수 없음
    - 일관성이 깨진다면 기대했던 결과와 다르게 나올 수 있음
