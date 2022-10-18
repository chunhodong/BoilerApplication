
### N + 1

* 조회한 데이터 개수만큼 연관관계에 있는 테이블을 조회하는 현상 쿼리를 한번 요청해도 N번만큼의 쿼리가 추가적으로 발생한다.
* JPQL이 연관관계에 있는 테이블을 조회하지 않기 때문에 발생한다.
* default_batch_fetch_size,fetchJoin중 상황에 맞게 사용해야한다.
  * default_batch_fetch_size는 fetchJoin으로 인하여 발생하는 문제점은 없지만 사이즈를 수동으로 변경해줘야한다. 
  * fetchJoin는 한번의 쿼리로 연관된 엔티티를 모두 조회하지만, fetchJoin으로 구현할 수 없는 상황존재이 존재한다.(on조건)

------

### default_batch_fetch_size

* 연관관계에있는 엔티티를 한꺼번에 읽어올 수 있는 개수를 지정한다.
* N + 1상황에서 조회된 데이터 개수만큼 연관관계 테이블을 조회하는 쿼리를 전송한다.

------

### fetchJoin
- fetchJoin과 일반join비교
  - fetchJoin은 N + 1 문제를 해결하기 위해 연관관계에있는 테이블까지 한꺼번에 조회한다.
    - querydsl에서 fetchJoin은 join방식을 규정하지 않는다, left join,right join,inner join등 모든 join에서 fetchJoin을 사용할 수 있다.
    - spring-data-jpa에서 @EntityGraph의 fetchJoin은 left join만 사용한다.
  - fetchJoin은 테이블의 모든 컬럼을 읽어오기때문에 상황에 따라 비효율적일 수 있다.(안쓰는 컬럼이 응답시간에 영향을 줄 수 있음)
  - 일반join은 join쿼리가 발생하지만 select절에서 연관관계테이블의 데이터는 조회하지 않는다.
  - 모든칼럼이 필요하지 않다면 상황에 따라 일반join을쓴다음 dto로 조회하는 방법이 효율적일 수 있다.
  - fetchJoin은 하나의 메소드만 정의해두면, 필요에 따라 여러dto를 만들 수 있다.
  - 일반join을 이용해서 dto로 조회한다면 dto가 화면에 의존하기때문에 요구사항에 따라 매번 조회메소드를 추가해야될 수 있다.<br/>

  
- fetchJoin에서 제한사항
  - on을 사용할 수 없음, on을 사용하여 연관관계에 대상에 조건을 걸면 db상태와 객체의 일관성이 깨질 수 있음(https://github.com/chunhodong/boilerApplication/blob/master/boiler/src/test/java/com/bronze/boiler/repository/CouponWalletRepositoryTest.java)
    - fetchJoin은 연관관계에 있는 엔티티가 모두 조회될걸 가정한다.
  - [fetchJoin에서 where은 사용할 수 있지만 상황에 따라 일관성이 깨질 가능성 존재한다.](https://github.com/chunhodong/boilerApplication/blob/master/boiler/src/test/java/com/bronze/boiler/repository/CouponWalletRepositoryTest.java)
    - outer join에서 where을 쓴다면 일관성을 보장할 수 없다.
    - fetchJoin에서 outer join은 왼쪽에 테이블의 모든 row가 보여지는걸 가정하기 때문에 where조건을 거는 순간 필터링으로 작용한다.
      - on에서 쓰는 조건과 where에서 쓰는 조건이 다르게 동작하기 떄문이다.
    - inner join에서도 대상쪽에 where을 쓴다면 일관성을 보장할 수 없다.
    - 일관성이 깨진다면 기대했던 결과와 다르게 나올 수 있음
      - 다른 결과가 나와야 되는 쿼리가 JPA캐시때문에 같은 결과가 나올수 있다.

- MultipleBagFetchException
  - 2개 이상의 OneToMany관계에있는 List타입의 엔티티를 fetchJoin했을때 발생하는 예외
  - 하이버네이트는 List타입의 OneToMany엔티티에 PersistentBag이라는 인스턴스를 할당에서 데이터를 추가
  - OneToMany관계가 있는 엔티티를 fetchJoin결과를 쿼리상에서 확인하면 주인테이블만 중복이 발생함
    이때 2개 이상의 OneToMany관계를 포함한 엔티티를  fetchJoin해서 쿼리를 확인하면 Many쪽테이블의 row가 중복발생됨(Team,Member관계에서 Member에 중복이 발생)
    이 중복된 데이터를 PersistentBag이 매핑하지 못하기때문에 예외가 발생
  - 해결 : OneToMany관계의 엔티티를 List가 아닌 Set으로처리하면됨,
