/*
 * Open Hospital (www.open-hospital.org)
 * Copyright © 2021 Informatici Senza Frontiere (info@informaticisenzafrontiere.org)
 *
 * Open Hospital is a free and open source software for healthcare data management.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * https://www.gnu.org/licenses/gpl-3.0-standalone.html
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.isf.utils.jobjects;

import java.awt.Component;

import javax.swing.JOptionPane;

import org.isf.generaldata.MessageBundle;
import org.isf.utils.exception.OHServiceException;
import org.isf.utils.exception.model.OHExceptionMessage;
import org.isf.utils.exception.model.OHSeverityLevel;

public class MessageDialog {

	public static final String ERROR_MESSAGE = MessageBundle.getMessage("angal.common.error.title");
	public static final String WARNING_MESSAGE = MessageBundle.getMessage("angal.common.warning.title");
	public static final String INFO_MESSAGE = MessageBundle.getMessage("angal.common.info.title");
	public static final String PLAIN_MESSAGE = MessageBundle.getMessage("angal.common.plain.title");
	public static final String QUESTION = MessageBundle.getMessage("angal.common.question.title");

	public static void error(Component parentComponent, String messageKey, Object... additionalArgs) {
		JOptionPane.showMessageDialog(
				parentComponent,
				(additionalArgs.length == 0)
						? MessageBundle.getMessage(messageKey)
						: MessageBundle.formatMessage(messageKey, additionalArgs),
				ERROR_MESSAGE,
				JOptionPane.ERROR_MESSAGE);
	}

	public static void warning(Component parentComponent, String messageKey, Object... additionalArgs) {
		JOptionPane.showMessageDialog(
				parentComponent,
				(additionalArgs.length == 0)
						? MessageBundle.getMessage(messageKey)
						: MessageBundle.formatMessage(messageKey, additionalArgs),
				WARNING_MESSAGE,
				JOptionPane.WARNING_MESSAGE);
	}

	public static void info(Component parentComponent, String messageKey, Object... additionalArgs) {
		JOptionPane.showMessageDialog(
				parentComponent,
				(additionalArgs.length == 0)
						? MessageBundle.getMessage(messageKey)
						: MessageBundle.formatMessage(messageKey, additionalArgs),
				INFO_MESSAGE,
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static void plain(Component parentComponent, String messageKey, Object... additionalArgs) {
		JOptionPane.showMessageDialog(
				parentComponent,
				(additionalArgs.length == 0)
						? MessageBundle.getMessage(messageKey)
						: MessageBundle.formatMessage(messageKey, additionalArgs),
				PLAIN_MESSAGE,
				JOptionPane.PLAIN_MESSAGE);
	}

	public static int yesNo(Component parentComponent, String messageKey, Object... additionalArgs) {
		return JOptionPane.showConfirmDialog(
				parentComponent,
				(additionalArgs.length == 0)
						? MessageBundle.getMessage(messageKey)
						: MessageBundle.formatMessage(messageKey, additionalArgs),
				QUESTION,
				JOptionPane.YES_NO_OPTION
		);
	}

	public static int yesNoCancel(Component parentComponent, String messageKey, Object... additionalArgs) {
		return JOptionPane.showConfirmDialog(
				parentComponent,
				(additionalArgs.length == 0)
						? MessageBundle.getMessage(messageKey)
						: MessageBundle.formatMessage(messageKey, additionalArgs),
				QUESTION,
				JOptionPane.YES_NO_CANCEL_OPTION
		);
	}

	public static int okCancel(Component parentComponent, String messageKey, Object... additionalArgs) {
		return JOptionPane.showConfirmDialog(
				parentComponent,
				(additionalArgs.length == 0)
						? MessageBundle.getMessage(messageKey)
						: MessageBundle.formatMessage(messageKey, additionalArgs),
				QUESTION,
				JOptionPane.OK_CANCEL_OPTION
		);
	}

	public static void showExceptions(OHServiceException ohServiceException) {
		if (ohServiceException.getMessages() == null) {
			return;
		}
		for (OHExceptionMessage ohExceptionMessage : ohServiceException.getMessages()) {
			OHSeverityLevel serverity = ohExceptionMessage.getLevel();
			if (OHSeverityLevel.ERROR == serverity) {
				error(null, ohExceptionMessage.getMessage());
			} else if (OHSeverityLevel.WARNING == serverity) {
				warning(null, ohExceptionMessage.getMessage());
			} else if (OHSeverityLevel.INFO == serverity) {
				info(null, ohExceptionMessage.getMessage());
			} else {
				plain(null, ohExceptionMessage.getMessage());
			}
		}
	}
}