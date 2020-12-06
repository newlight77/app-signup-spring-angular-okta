export class LoginModel {
  id: number;
  username: string;
  loginDate: Date;
  ipAddress: string;
  city: string;
  region: string;
  device: string;
  success: boolean;
}

export class IpAddressModel {
  ip: string;
  city: string;
  postal: string;
  countryName: string;
  region: string;
  timezone: string;
  longitude: string;
  latitude: string;
}
