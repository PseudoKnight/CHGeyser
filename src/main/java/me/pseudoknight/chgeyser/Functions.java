package me.pseudoknight.chgeyser;

import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.abstraction.MCPlayer;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.MSVersion;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.*;
import com.laytonsmith.core.environments.CommandHelperEnvironment;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import com.laytonsmith.core.natives.interfaces.Mixed;
import org.geysermc.geyser.api.connection.GeyserConnection;
import org.geysermc.geyser.api.GeyserApi;

import java.util.UUID;

public class Functions {
	public static String docs() {
		return "Provides Geyser functions for handling Bedrock clients.";
	}

	private static GeyserConnection GetGeyserConnection(Target t, Environment environment, Mixed... args) {
		UUID uuid;
		if(args.length > 0) {
			uuid = Static.GetPlayer(args[0].val(), t).getUniqueID();
		} else {
			MCPlayer p = environment.getEnv(CommandHelperEnvironment.class).GetPlayer();
			if(p == null) {
				return null;
			}
			uuid = p.getUniqueID();
		}
		return GeyserApi.api().connectionByUuid(uuid);
	}

	public static abstract class GeyserFunction extends AbstractFunction {
		@Override
		public boolean isRestricted() {
			return true;
		}

		@Override
		public Boolean runAsync() {
			return false;
		}

		@Override
		public Version since() {
			return MSVersion.V3_3_5;
		}

		@Override
		public Class<? extends CREThrowable>[] thrown() {
			return new Class[]{};
		}
	}

	@api
	public static class geyser_connected extends GeyserFunction {
		@Override
		public String getName() {
			return "geyser_connected";
		}

		@Override
		public String docs() {
			return "boolean {[player]} Returns whether a player is connected using a Bedrock client.";
		}

		@Override
		public Integer[] numArgs() {
			return new Integer[]{0, 1};
		}

		@Override
		public Mixed exec(Target t, Environment environment, Mixed... args) throws ConfigRuntimeException {
			return CBoolean.get(GetGeyserConnection(t, environment, args) != null);
		}
	}

	@api
	public static class geyser_input_mode extends GeyserFunction {
		@Override
		public String getName() {
			return "geyser_input_mode";
		}

		@Override
		public String docs() {
			return "string {[player]} Returns the player's client input mode."
					+ " Will be null if player is not using a Bedrock client."
					+ " Otherwise will return KEYBOARD_MOUSE, TOUCH, CONTROLLER, VR, or UNKNOWN.";
		}

		@Override
		public Integer[] numArgs() {
			return new Integer[]{0, 1};
		}

		@Override
		public Mixed exec(Target t, Environment environment, Mixed... args) throws ConfigRuntimeException {
			GeyserConnection connection = GetGeyserConnection(t, environment, args);
			if(connection == null) {
				return CNull.NULL;
			}
			return new CString(connection.inputMode().name(), t);
		}
	}

	@api
	public static class geyser_xuid extends GeyserFunction {
		@Override
		public String getName() {
			return "geyser_xuid";
		}

		@Override
		public String docs() {
			return "string {[player]} Returns the player's XUID."
					+ " Returns null if player is not using a bedrock client.";
		}

		@Override
		public Integer[] numArgs() {
			return new Integer[]{0, 1};
		}

		@Override
		public Mixed exec(Target t, Environment environment, Mixed... args) throws ConfigRuntimeException {
			GeyserConnection connection = GetGeyserConnection(t, environment, args);
			if(connection == null) {
				return CNull.NULL;
			}
			return new CString(connection.xuid(), t);
		}
	}

	@api
	public static class geyser_name extends GeyserFunction {
		@Override
		public String getName() {
			return "geyser_name";
		}

		@Override
		public String docs() {
			return "string {[player]} Returns the player's gamertag."
					+ " Returns null if player is not using a bedrock client.";
		}

		@Override
		public Integer[] numArgs() {
			return new Integer[]{0, 1};
		}

		@Override
		public Mixed exec(Target t, Environment environment, Mixed... args) throws ConfigRuntimeException {
			GeyserConnection connection = GetGeyserConnection(t, environment, args);
			if(connection == null) {
				return CNull.NULL;
			}
			return new CString(connection.bedrockUsername(), t);
		}
	}
}
