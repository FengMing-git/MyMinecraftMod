package net.fengming.myfirstmod.block;

import net.fengming.myfirstmod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.fengming.myfirstmod.MyFirstMod;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(MyFirstMod.MODID);

    public static final DeferredBlock<Block> BISMUTH_BLOCK = registerBlock("bismuth_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f)
                    .sound(SoundType.AMETHYST)));

    // 延迟注册一个方块，同时把这个方块注册成item
    // 方块需要的assets有assets/mod/
    // 状态配置文件blockstates/.json
    // 模型配置文件models/block/.json，指定使用的贴图
    //
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block)
    {
        DeferredBlock<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name ,toReturn);
        return toReturn;
    }

    // 把一个block延迟注册成一个item
    // item需要assets/mod/
    // 模型配置文件models/item/.json，指定贴图文件
    // 贴图文件textures/item/.png
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block)
    {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }


    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }

}
