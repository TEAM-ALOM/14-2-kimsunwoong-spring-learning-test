package cholog;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QueryingDAO {
    private JdbcTemplate jdbcTemplate;

    public QueryingDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Customer> actorRowMapper = (resultSet, rowNum) -> {
        Customer customer = new Customer(
                resultSet.getLong("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name")
        );
        return customer;
    };
    //추후 rowMapper에 대해 학습해보고 이용해보기


    /**
     * public <T> T queryForObject(String sql, Class<T> requiredType)
     */
    public int count() {
        //TODO : customers 디비에 포함되어있는 row가 몇개인지 확인하는 기능 구현
        String sql = "SELECT COUNT(*) FROM customers"; // sql 명령, 전체 행 수 반환

        return jdbcTemplate.queryForObject(sql, Integer.class); //queryForObject(String sql, Class<T> requiredType)
    } // sql 커리 문자열과 타입을 인자로 전달

    /**
     * public <T> T queryForObject(String sql, Class<T> requiredType, @Nullable Object... args)
     */
    public String getLastName(Long id) {
        //TODO : 주어진 Id에 해당하는 customers의 lastName을 반환
        String sql = "SELECT last_name FROM customers WHERE id = ?";
        // ? 기호는 매개변수 바인딩을 위한 플레이스 홀더
        return jdbcTemplate.queryForObject(sql, String.class, id);
    } // 주어진 고객 ID를 기반으로 테이블에 해당 고객의 lastname 반환

    /**
     * public <T> T queryForObject(String sql, RowMapper<T> rowMapper, @Nullable Object... args)
     */
    public Customer findCustomerById(Long id) {
        String sql = "select id, first_name, last_name from customers where id = ?";
        //TODO : 주어진 Id에 해당하는 customer를 객체로 반환
        return jdbcTemplate.queryForObject(sql, actorRowMapper, id);
        /*
        RowMapper는 SQL 쿼리 결과를 Java 객체로 변환하는데 사용됨.
        이 경우 actorRowMapper는 ResultSet에서 데이터를 가져와 Customer 객체를 생성
        (resultSet, rowNum) -> {
                    Customer customer = new Customer(
                            resultSet.getLong("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name")
                    );
                    return customer;
                }
         */
    }

    /**
     * public <T> List<T> query(String sql, RowMapper<T> rowMapper)
     */
    public List<Customer> findAllCustomers() {
        String sql = "select id, first_name, last_name from customers";
        //TODO : 저장된 모든 Customers를 list형태로 반환
        return jdbcTemplate.query(sql, actorRowMapper);
    }

    /**
     * public <T> List<T> query(String sql, RowMapper<T> rowMapper, @Nullable Object... args)
     */
    public List<Customer> findCustomerByFirstName(String firstName) {
        String sql = "select id, first_name, last_name from customers where first_name = ?";
        //TODO : firstName을 기준으로 customer를 list형태로 반환
        return jdbcTemplate.query(sql, actorRowMapper, firstName);
    }
}
