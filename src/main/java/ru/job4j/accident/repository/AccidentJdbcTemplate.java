package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Repository
public class AccidentJdbcTemplate implements AccidentStore {
    private final JdbcTemplate jdbc;

    private final RowMapper<Rule> ruleMapper = (resultSet, rowNum) ->
            Rule.of(resultSet.getInt("id"),
            resultSet.getString("name"));

    private final RowMapper<AccidentType> typeMapper = (resultSet, rowNum) ->
            AccidentType.of(resultSet.getInt("id"),
            resultSet.getString("name"));

    private final RowMapper<Accident> accidentMapper = (resultSet, rowNum) ->
            new Accident(resultSet.getInt("id"),
            resultSet.getString("name"), resultSet.getString("text"),
            resultSet.getString("address"),
            AccidentType.of(resultSet.getInt("t_id"), resultSet.getString("t_name")));


    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Collection<Accident> getAllAccidents() {
        List<Accident> accidents = jdbc.query(
                "select a.id, a.name, a.text, a.address, t.id as t_id, t.name as t_name from " +
                        "accidents a join accident_types t on a.accident_type = t.id ", accidentMapper);
        for (Accident accident : accidents) {
            List<Rule> rules = jdbc.query(
                    "select r.id, r.name from " +
                            "accidents_rules ar join rules r on ar.rule_id = r.id " +
                            "where ar.accident_id = ?", ruleMapper, accident.getId());
            rules.forEach(accident::addRule);
        }
        return accidents;
    }

    public Collection<AccidentType> getAllTypes(){
        return jdbc.query("select id, name from accident_types", typeMapper);
    }

    public Collection<Rule> getAllRules(){
        return jdbc.query("select id, name from rules", ruleMapper);
    }

    @Override
    public void addAccident(Accident accident, int typeId, int[] rIds) {
        if (accident.getId() == 0) {
            addNewAccident(accident, typeId, rIds);
        } else {
            upDateAccident(accident);
        }
    }
    private void addNewAccident(Accident accident, int typeId, int[] rIds) {
        String insertSql = "insert into accidents(name, text, address, accident_type) " +
                "values(?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertSql, new String[]{"id"});
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, typeId);
            return ps;
        }, keyHolder);
        int generatedId = keyHolder.getKey().intValue();
        Arrays.stream(rIds).forEach(rId -> jdbc.update(
                "insert into accidents_rules(accident_id, rule_id) values (?,?)",
                generatedId, rId)
        );
    }

    private void upDateAccident(Accident accident) {
        jdbc.update("update accidents set name = ?, text = ?, address = ? where id = ?",
                accident.getName(), accident.getText(), accident.getAddress(), accident.getId());
    }

    @Override
    public Accident getAccidentById(int id) {
        Accident accident = jdbc.queryForObject(
                "select a.id, a.name, a.text, a.address, t.id as t_id, t.name as t_name from " +
                        "accidents a join accident_types t on a.accident_type = t.id " +
                        "where a.id = ?", accidentMapper, id);
        List<Rule> rules = jdbc.query(
                "select r.id, r.name from " +
                        "accidents_rules ar join rules r on ar.rule_id = r.id " +
                        "where ar.accident_id = ?", ruleMapper, id);
        rules.forEach(accident::addRule);
        return accident;
    }

}
