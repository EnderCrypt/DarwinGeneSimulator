import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Genome
	{
	public List<Gene> genes = new ArrayList<>();
	private Memory memory;
	Genome(Memory memory)
		{
		this.memory = memory;
		int genesToAdd = 100 + (int) (Math.random()*100);
		for (int i=0;i<genesToAdd;i++)
			{
			genes.add(new Gene(memory));
			}
		}
	public void printGenome()
		{
		System.out.println("Debug print of "+this+" ("+genes.size()+" genes)");
		Iterator<Gene> iterator = genes.iterator();
		int conditionalStatus = 0;
		String conditionalStatusMarker = "";
		while(iterator.hasNext())
			{
			Gene gene = iterator.next();
			switch (gene.operator)
				{
				case STATICNUMBER:
					System.out.println(conditionalStatusMarker+gene.geneNumberData);
				break;
				case GETSTATICMEMORY:
					System.out.println(conditionalStatusMarker+"."+gene.geneNumberData+" (Memory: "+memory.get(gene.geneNumberData)+")");
				break;
				case CONDITIONAL:
					switch(conditionalStatus)
						{
						case 0:
							conditionalStatus = 1;
							conditionalStatusMarker = "?\t";
							System.out.println("["+gene.operator.name()+"s]IF\n{");
						break;
						case 1:
							conditionalStatus = 2;
							conditionalStatusMarker = "\t";
							System.out.println("}\n["+gene.operator.name()+"]THEN\n{");
						break;
						case 2:
							conditionalStatus = 0;
							conditionalStatusMarker = "";
							System.out.println("}\n["+gene.operator.name()+"]END");
							break;
						}
				break;
				default:
					System.out.println(conditionalStatusMarker+gene.operator.name());
				}
			}
		//code end
		if (conditionalStatus > 0)
			{
			System.out.println("GENOME ended whitout finishing conditional statments correctly");
			}
		}
	public void execute(Bot bot)
		{
		Stack<Integer> intStack = new Stack<>();
		Stack<Boolean> boolStack = new Stack<>();
		
		Iterator<Gene> iterator = genes.iterator();
		
		ExecFlow execFlow = new ExecFlow(bot, iterator, intStack, boolStack);
		
		while(iterator.hasNext())
			{
			execFlow.gene = iterator.next();
			execFlow.gene.execute(execFlow);
			}
		}
	class ExecFlow
		{
		Bot bot;
		Stack<Integer> intStack;
		Stack<Boolean> boolStack;
		Iterator<Gene> iterator;
		int conditionalStatus;
		Gene gene;
		public ExecFlow(Bot bot, Iterator<Gene> iterator, Stack<Integer> intStack, Stack<Boolean> boolStack)
			{
			this.bot = bot;
			this.iterator = iterator;
			this.intStack = intStack;
			this.boolStack = boolStack;
			this.conditionalStatus = 0;
				// 0 = outside
				// 1 = conditional
				// 2 = execute code
			}
		}
	}
