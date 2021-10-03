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
import java.util.*;

@Repository
public class AccidentJdbcTemplate implements AccidentStore {
    private final JdbcTemplate jdbc;

    private final RowMapper<Rule> ruleMapper = (resultSet, rowNum) ->
            Rule.of(resultSet.getInt("id"),
            resultSet.getString("name"));

    private final RowMapper<AccidentType> typeMapper = (resultSet, rowNum) ->
            AccidentType.of(resultSet.getInt("id"),
            resultSet.getString("name"));

    private final RowMapper<Accident> accidentMapper = (resultSet, rowNum) -> {
       Accident accident =  new Accident(resultSet.getInt("id"),
                resultSet.getString("name"), resultSet.getString("text"),
                resultSet.getString("address"),
                AccidentType.of(resultSet.getInt("t_id"), resultSet.getString("t_name")));
       accident.addRule(Rule.of(resultSet.getInt("r_id"), resultSet.getString("r_name")));
       return accident;
    };

    private Collection<Accident> mergeAccidentsWithSameId(List<Accident> accidents) {
        Map<Integer, Accident> accidentMap = new LinkedHashMap<>();
        for(Accident accident : accidents) {
            Accident computedAccident = accidentMap.computeIfPresent(accident.getId(), (k, v) -> {
                v.addRule(accident.getRules().iterator().next());
                return v;
            });
            if (computedAccident == null) {
                accidentMap.put(accident.getId(), accident);
            }
        }
        return accidentMap.values();
    }

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Collection<Accident> getAllAccidents() {
        List<Accident> accidents = jdbc.query(
                "select a.id, a.name, a.text, a.address, t.id t_id , t.name t_name, r.id r_id, r.name r_name from " +
                        "accidents a join accident_types t on a.accident_type = t.id " +
                        "join accidents_rules ar on a.id = ar.accident_id " +
                        "join rules r on ar.rule_id = r.id", accidentMapper);

        return mergeAccidentsWithSameId(accidents);
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
            updateAccident(accident);
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

    private void updateAccident(Accident accident) {
        jdbc.update("update accidents set name = ?, text = ?, address = ? where id = ?",
                accident.getName(), accident.getText(), accident.getAddress(), accident.getId());
    }

    @Override
    public Accident getAccidentById(int id) {
        List<Accident> accidents = jdbc.query(
                "select a.id, a.name, a.text, a.address, t.id t_id , t.name t_name, r.id r_id, r.name r_name from " +
                        "accidents a join accident_types t on a.accident_type = t.id " +
                        "join accidents_rules ar on a.id = ar.accident_id " +
                        "join rules r on ar.rule_id = r.id where a.id = ?", accidentMapper, id);

        return mergeAccidentsWithSameId(accidents).iterator().next();
    }
}
