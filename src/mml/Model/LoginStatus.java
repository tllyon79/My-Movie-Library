package mml.Model;

/**
 * Enum that represents the potential results of AccountManager.AttemptLogIn
 */
public enum LoginStatus {
    Complete,
    Failed_AccountExistError,
    Failed_IncorrectPassword
}
