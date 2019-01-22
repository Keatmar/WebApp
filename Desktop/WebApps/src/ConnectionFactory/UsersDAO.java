package ConnectionFactory;

import java.util.List;

import model.User;

public interface UsersDAO {
	public User getUserByEmailOrUsername(String input);
	public void createUser(User users);
	public User searchUserById(int id);
	public User loginUser(String username, String password);
	public List<User> list();
	public void confirmUser(int id);
	public void deleteUser(Long id);
	public void editUser(User users);

}
