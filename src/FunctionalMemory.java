public enum FunctionalMemory
	{
	ENERGY(1,(bot, memoryLocation) ->
		{
		bot.memory.put(memoryLocation, bot.energy);
		}),
	AGE(2,(bot, memoryLocation) ->
		{
		bot.age++;
		bot.memory.put(memoryLocation,bot.age);
		});
	
	private final int memory;
	private final FunctionalMemoryInterface memoryExecutor;
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
