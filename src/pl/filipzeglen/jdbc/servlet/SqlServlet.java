package pl.filipzeglen.jdbc.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.filipzeglen.jdbc.data.City;
import pl.filipzeglen.jdbc.db.DbUtil;

@WebServlet("/SqlServlet")
public class SqlServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String param = request.getParameter("get");
        if ("show".equals(param)) {
            try {
                List<City> cityList = getCities();
                request.setAttribute("cityList", cityList);
                request.getRequestDispatcher("citylist.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(500);
            }
        } else {
            response.sendError(403);
        }
    }

    private List<City> getCities() throws SQLException {
        List<City> cityList = null;
        final String sqlQuery = "SELECT Name, Population FROM city";
        try (Connection conn = DbUtil.getInstance().getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery);) {
            String cityName = null;
            int cityPopulation = 0;
            cityList = new ArrayList<>();
            while (resultSet.next()) {
                cityName = resultSet.getString("Name");
                cityPopulation = resultSet.getInt("Population");
                City city = new City(cityName, cityPopulation);
                cityList.add(city);
            }
        }

        return cityList;
    }

    @Override
    public void destroy() {
        DbUtil.getInstance().close();
    }
}