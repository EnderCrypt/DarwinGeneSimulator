public class Memory
	{
	private int[] memory;
	Memory(int size)
		{
		memory = new int[size];
		flashMemory();
		}
	public boolean put(int location, int data)
		{
		if ((location >= 0) && (location < memory.length))
			{
			memory[location] = data;
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
