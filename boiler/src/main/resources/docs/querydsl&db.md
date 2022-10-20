### Subquery

* 서브쿼리는 쿼리안에 존재하는 또 다른 쿼리를 말한다.
* 서브쿼리는 select절,from절,where절에 올 수 있다.
* from절에 오는 서브쿼리를 인라인뷰라고 부른다.
* 서브쿼리가 단 하나의 행만 반환하는 경우를 스칼라 서브쿼리라고 부른다.
* 스칼라 서브쿼리는 칼럼갯수에따라 select절이나 where절에 올 수 있다.
* 서브쿼리를 실행하는데 바깥쪽에 쿼리에 값이 이용되는 경우를 상관서브 쿼리라고 부른다
* 바깥쪽 서브쿼리와 관계없이 실행되는 서브쿼리를 단독 서브쿼리라고 부른다.
* [서브쿼리는 종류나 위치에따라 성능이 떨어질 수 있다. 이 경우 서브쿼리를 join으로 바꾸거나, 쿼리를 나눠서 실행하면 해결할 수 있다.](https://github.com/chunhodong/boilerApplication/blob/master/boiler/src/test/java/com/bronze/boiler/repository/MemberRepositoryTest.java)
------

### [Bulk Update](https://github.com/chunhodong/boilerApplication/blob/master/boiler/src/test/java/com/bronze/boiler/repository/MemberRepositoryTest.java)
* JPA는 1차 캐시에 저장된 초기 스냅샷과 현재 엔티티를 비교하는 DirtyChecking을 통해서 update쿼리를 날리게된다.
* 많은 엔티티를 수정할때, 엔티티당 하나의 update쿼리가 날라가기때문에 시간이 많이 걸리게 된다.
* querydsl을 이용하면 하나의 update쿼리만 날리기떄문에 성능상 장점이 존재한다.
* 하지만 querydsl로 처리한 update는 1차 캐시를 갱신하지 않는 단점이 존재한다.
  * update후 조회를 하게 되면 불일치가 발생할 수 있다
* 대량의 데이터를 한꺼번에 업데이트할떄는 querydsl로 하는게 성능상 장점이 존재할 수 있지만 1차 캐시 갱신여부를 확인해야한다.