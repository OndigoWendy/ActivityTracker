package dao;
import  models.Ranger;

import java.util.List;

public interface RangerDao {
   void save();

   List<Ranger> all();
}
