package tictim.paraglider.contents.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import tictim.paraglider.utils.ParagliderUtils;

import javax.annotation.Nonnull;

public class VesselLoot extends LootModifier{
	public static final Codec<VesselLoot> CODEC = RecordCodecBuilder.create(inst ->
			codecStart(inst)
					.and(Codec.INT.fieldOf("count").forGetter(m -> m.count))
					.apply(inst, VesselLoot::new));

	private final int count;

	public VesselLoot(LootItemCondition[] conditionsIn){
		this(conditionsIn, 0);
	}
	public VesselLoot(LootItemCondition[] conditionsIn, int count){
		super(conditionsIn);
		this.count = count;
	}

	@Nonnull @Override protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context){
		Item item = ParagliderUtils.getAppropriateVessel();
		if(item!=null) generatedLoot.add(new ItemStack(item, count));
		return generatedLoot;
	}

	@Override public Codec<? extends IGlobalLootModifier> codec(){
		return CODEC;
	}
}