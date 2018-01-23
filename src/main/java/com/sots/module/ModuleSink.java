package com.sots.module;

import java.util.ArrayList;
import java.util.UUID;

import com.sots.tiles.TileGenericPipe;
import com.sots.tiles.TileRoutedPipe;
import com.sots.util.data.Tuple;

import net.minecraft.item.Item;

public class ModuleSink extends ModuleBase implements IModule{
	
	private boolean hasRegistered = false;
	
	@Override
	// TODO: 21-1-2018
	public boolean execute(TileGenericPipe te) {
		/*if(!hasRegistered) {
			if(te.hasInventory() && te.hasNetwork()) {
				ArrayList<Item> types = te.getItemTypesInInventory(te.getNetwork().getDirectionForDestination(te.nodeID));
				if(!types.isEmpty()) {
					types.forEach(p -> te.getNetwork().registerItemStorage(new Tuple<>(te.nodeID, p)));
					hasRegistered=true;
				}
			}
		}
		return true;*/
		return false;
	}
	
	@Override
	public boolean canExecute() {return true;}
	
	@Override
	public boolean canInsert() {return true;}
	
	@Override
	public ModuleType modType() {return ModuleType.SINK;}
	
	@Override
	public void disconnect() {
		//has to be disconnected!
		hasRegistered=false;
	}

}
