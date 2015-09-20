import java.util.ArrayList;
import java.util.List;

public class Stack<T>
	{
	private List<T> stack;
	Stack()
		{
		stack = new ArrayList<>();
		}
	public int size()
		{
		return stack.size();
		}
	public void clear()
		{
		stack.clear();
		}
	public void push(T data)
		{
		stack.add(data);
		}
	public T pull()
		{
		if (stack.size() > 0)
			{
			return stack.remove(stack.size()-1);
			}
		return null;
		}
	public int pullInt()
		{
		T data = pull();
		if (data == null)
			{
			return 0;
			}
		return (int) data;
		}
	public boolean pullBool()
		{
		T data = pull();
		if (data == null)
			{
			return true;
			}
		return (boolean) pull();
		}
	}
