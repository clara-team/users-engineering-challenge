export const config = {
  domain:   import.meta.env.VITE_AUTH0_DOMAIN as string,
  clientId: import.meta.env.VITE_AUTH0_CLIENT_ID as string,
  audience: import.meta.env.VITE_AUTH0_AUDIENCE as string,
  apiBase:  (import.meta.env.VITE_API_BASE as string) ?? 'http://localhost:8080',
};
