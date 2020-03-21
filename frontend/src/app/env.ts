export class Env {

    // The values that are defined here are the default values that can
    // be overridden by env.js
  
    public apiUrl = '';
  
    public enableDebug = true;
  
    public oktaConfig = {};
  
    constructor() {
    }
  
    public static get() : Env {

        const env = new Env();

        // Read environment variables from browser window
        const browserWindow = window || {};
        const browserWindowEnv = browserWindow['__env'] || {};

        // Assign environment variables from browser window to env
        // In the current implementation, properties from env.js overwrite defaults from the EnvService.
        // If needed, a deep merge can be performed here to merge properties instead of overwriting them.
        for (const key in browserWindowEnv) {
            if (browserWindowEnv.hasOwnProperty(key)) {
            env[key] = window['__env'][key];
            }
        }

        console.log(env);

        return env;
    }
}
