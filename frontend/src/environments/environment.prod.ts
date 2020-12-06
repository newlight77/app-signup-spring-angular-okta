import { Config } from './config';

export const environment = {
  production: true,
  enableDebug: false,
  env: Config.get().env,
};
