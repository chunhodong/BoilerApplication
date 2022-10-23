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

------


### [Spring-Data-Jpa의 deleteAllById,deleteAllByIdInBatch](https://github.com/chunhodong/boilerApplication/blob/master/boiler/src/test/java/com/bronze/boiler/repository/CouponRepositoryTest.java)
* deleteAllById는 아이디에 해당하는 엔티티들을 조회한후에 아이디 개수만큼 삭제쿼리를 전송
* deleteAllByIdInBatch는 엔티티 조회없이 하나의 쿼리로 삭제전송
* deleteAllById는 아이디에 대한 검증이 이뤄지기때문에 아이디에 해당하는 엔티티가 없으면 예외발생
    deleteAllByIdInBatch는 아이디에 대한 검증없이 삭제 쿼리 전송, 아이디에 대한 엔티티가 없어도 예외발생안함

------

### Spring-Data-Jpa의 existsById,exists
* existsById는 select count(*) from table where id=? 형식의 쿼리생성
* exists는 select * from table where id = ? 형식의 쿼리생성
* 효율화를 위해 native하게 exists쿼리를 생성하는방법이나 querydsl에서 limit구문을 사용할 수 있게 수정
* count가 들어가는 순간 조건해 해당하는 모든 row를 하나씩 카운팅

------

### 조회속도
* 인덱스 스캔범위 줄이기, 테이블 엑세스 횟수 줄이기
* 인덱스 스캔보다 테이블 스캔이 더 빠를 수 있음
* 인덱스 스캔에서 테이블 엑세스는 랜덤엑세스인 반면, 테이블 스캔에서 테이블엑세스는 시퀀셜 엑세스
* 인덱스 스캔에서 테이블 엑세스는 Single Block Read, 테이블 스캔에서 테이블 엑세스는 Multi Block Read
* 옵티마이저가 판단했을때 테이블 스캔 비용이 더 적을경우 인덱스가 있어도 테이블스캔
* 테이블 스캔을 줄이기 위한 방법 중 커버링 인덱스를 만들수 있으나 너무 많은 컬럼이 있덱스에 있으면 테이블 스캔과 차이가 없을 수 있음


