public class Memory
	{
	private int[] memory;
	private int minNumber;
	private int maxNumber;
	Memory(int size, int minNumber, int maxNumber)
		{
		memory = new int[size];
		this.minNumber = minNumber;
		this.maxNumber = maxNumber;
		flashMemory();
		}
	public int getMinNumber()
		{
		return minNumber;
		}
	public int getMaxNumber()
		{
		return maxNumber;
		}
	public boolean put(int location, int number)
		{
		if ((location >= 0) && (location < memory.length))
			{
			number = Math.max(number, minNumber);
			number = Math.min(number, maxNumber);
			memory[location] = number;
			return true;
			}
		else
			{
			return false;
			}
		}
	public void flashMemory()
		{
		for (int i=0;i<memory.length;i++)
			{
			memory[i] = 0;
			}
		}
	public void flashFunctionalMemory()
		{
		for (FunctionalMemory fm: FunctionalMemory.values())
			{
			memory[fm.getMemoryLocation()] = 0;
			}
		}
	public void executeFunctionalMemory(Bot bot)
		{
		for (FunctionalMemory fm: FunctionalMemory.values())
			{
			fm.execute(bot,fm.getMemoryLocation());
			}
		}
	public void printMemory()
		{
		int num = 0;
		while (num < memory.length)
			{
			String out = num+":\t";
			for (int i=0;(num+i<memory.length) && (i<10);i++)
				{
				out = out+memory[num+i]+'\t';
				}
			System.out.println(out);
			num += 10;
			}
		}
	}
