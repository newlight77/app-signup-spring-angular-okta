export class RegisterModel {
    username: string;
    password: boolean;
    lastname: Date;
    firstname: string;
    phoneNumber: string;
    activationCode: string;
}

export class RegisterStateModel {
    username: string
    oktaRegistered: boolean
    emailSent: boolean
    emailValidated: boolean
    activationCodeSent: boolean
    activatedByCode: boolean
    resumeUploaded: boolean
    statusUpdated: boolean
    validated: boolean
}
