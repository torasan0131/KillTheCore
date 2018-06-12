package net.disaapsesv.gamecore.killthecore.event;

import org.bukkit.entity.Player;

import net.disaapsesv.gamecore.killthecore.mysql.SQL;

public class DeathKtcEvent {

	public static void KilledAddScore (String uuid ,String score ,Player player) {
		Object o = SQL.get("Dscore", uuid, "!=", "1234567890", "KTCscore");
		SQL.set("KTCscore",o + score, uuid, "=", uuid, "KTCscore");
		player.sendMessage("+" + o + "Score by killed player");
	}
}
