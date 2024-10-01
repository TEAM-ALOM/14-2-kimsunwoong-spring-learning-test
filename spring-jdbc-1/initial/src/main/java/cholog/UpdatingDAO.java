package cholog;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class UpdatingDAO {
    private JdbcTemplate jdbcTemplate;

    public UpdatingDAO(JdbcTemplate jdbcTemplate) {
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
     * public int update(String sql, @Nullable Object... args)
     */
    public void insert(Customer customer) {
        //todo: customer를 디비에 저장하기
        String sql = "INSERT INTO customers (first_name, last_name) VALUES (?, ?)";
        jdbcTemplate.update(sql, customer.getFirstName(), customer.getLastName());
    }
    // update 메소드는 데이터베이스에 INSERT, UPDATE, DELETE 쿼리를 실행할 때 사용됨
    // ? 는 바인딩될 매개변수를 의미하고 customer.getFirstName()과 customer.getLastName()을 통해 Customer 객체의 이름과 성을 쿼리에 전달
    /**
     * public int update(String sql, @Nullable Object... args)
     */
    public int delete(Long id) {
        //todo: id에 해당하는 customer를 지우고, 해당 쿼리에 영향받는 row 수반환하기
        String sql = "DELETE FROM customers WHERE id = ?";
        return jdbcTemplate.update(sql, id);
        // 삭제된 행의 수 반환
    }

    /**
     * public int update(final PreparedStatementCreator psc, final KeyHolder generatedKeyHolder)
     */
    public Long insertWithKeyHolder(Customer customer) {
        String sql = "insert into customers (first_name, last_name) values (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        //KeyHolder는 데이터베이스에서 생성된 자동 증가 키 값을 저장하는 데 사용됨
        //GeneratedKeyHolder는 이 값을 저장하는 구현체
        //PreparedStatement의 두 번째 인자로 new String[] {"id"}를 전달하여, 삽입 후 생성된 id 값을 가져오도록 설정

        //todo : keyHolder에 대해 학습하고, Customer를 저장후 저장된 Customer의 id를 반환하기

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] {"id"});
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            return ps;
        }, keyHolder);
        // PreparedStatementCreator를 통해 SQL 쿼리를 준비하고 실행하며, KeyHolder에 생성된 키 값을 저장

        //  새로운 고객을 삽입할 때 해당 고객의 id 값 가져옴
        Long id = keyHolder.getKey().longValue();

        return keyHolder.getKey().longValue();
    }
}
