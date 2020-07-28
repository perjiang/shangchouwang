import com.alibaba.druid.pool.DruidDataSource;
import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.service.api.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
public class TestMybatis {

    @Autowired
    DataSource dataSource;

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    AdminService adminService;

    @Test
    public void testTx(){
        Admin admin = new Admin(null, "mht", "123456", "马化腾", "mht@qq.com", null);
        adminService.saveAdmin(admin);
    }


    @Test
    public void testDatasource() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    public void testMapper(){
        Admin admin = adminMapper.selectByPrimaryKey(1);
        System.out.println(admin);
    }

    @Test
    public void  testLog(){
        //获取日志对象
        Logger logger = LoggerFactory.getLogger(TestMybatis.class);
        logger.debug("aaa");
    }
}
