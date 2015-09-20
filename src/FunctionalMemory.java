public enum FunctionalMemory
	{
	ENERGY(0,(bot, memoryLocation) ->
		{
		bot.memory.put(memoryLocation, bot.energy);
		}),
	AGE(1,(bot, memoryLocation) ->
		{
		bot.age++;
		bot.memory.put(memoryLocation,bot.age);
		});
	
	private int memory;
	private FunctionalMemoryInterface memoryExecutor;
	private FunctionalMemory(int memory, FunctionalMemoryInterface memoryExecutor)
		{
		this.memory = memory;
		this.memoryExecutor = memoryExecutor;
		}
	public int getMemoryLocation()
		{
		return memory;
		}
	public void execute(Bot bot, int memoryLocation)
		{
		memoryExecutor.execute(bot, memoryLocation);
		}
	}
