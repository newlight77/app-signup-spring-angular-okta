import { Config } from './config';

export const environment = {
  production: true,
  config: Config.get(),
  enableDebug: false
};

