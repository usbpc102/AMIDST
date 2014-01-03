package amidst.gui.version;

import java.net.MalformedURLException;

import MoF.FinderWindow;
import amidst.Options;
import amidst.logging.Log;
import amidst.minecraft.Minecraft;
import amidst.minecraft.MinecraftUtil;
import amidst.minecraft.remote.RemoteMinecraft;
import amidst.version.MinecraftProfile;

public class DebugRemoteComponent extends VersionComponent {

	public DebugRemoteComponent(MinecraftProfile profile) {
		super(profile);
	}
	
	public void load() {
		isLoading = true;
		repaint();
		Options.instance.lastProfile.set(profile.getProfileName());
		(new Thread(new Runnable() {
			@Override
			public void run() {
				MinecraftUtil.setBiomeInterface(new RemoteMinecraft());
				new FinderWindow();
				VersionSelectWindow.get().dispose();
			}
		})).start();
	}
}
