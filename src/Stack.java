import java.util.ArrayList;
import java.util.List;

public class Stack <T>
	{
	private T defaultReturn;
	private List<T> stack;
	Stack(T defaultReturn)
		{
		stack = new ArrayList<>();
		this.defaultReturn = defaultReturn;
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
	public static boolean intToBoolean(int number, int minNumber , int maxNumber)
		{
		double chance = minNumber+(Math.random()*(maxNumber-minNumber));
		return (number >= chance);
		}
	public static boolean intToBoolean(int number, int maxNumber)
		{
		return intToBoolean(number, 0 , maxNumber);
		}
	public T pull()
		{
		if (stack.size() > 0)
			{
			return stack.remove(stack.size()-1);
			}
		else
			{
			return defaultReturn;
			}
		}
	}
