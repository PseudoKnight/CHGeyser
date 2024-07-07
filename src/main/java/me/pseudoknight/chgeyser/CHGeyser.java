package me.pseudoknight.chgeyser;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.extensions.AbstractExtension;
import com.laytonsmith.core.extensions.MSExtension;

@MSExtension("CHGeyser")
public class CHGeyser extends AbstractExtension {

	public Version getVersion() {
		return new SimpleVersion(1,0,0);
	}

	@Override
	public void onStartup() {
		Static.getLogger().info("CHGeyser " + getVersion() + " loaded.");
	}

	@Override
	public void onShutdown() {
		Static.getLogger().info("CHGeyser " + getVersion() + " unloaded.");
	}
}
