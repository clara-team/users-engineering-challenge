import { useAuth0 } from '@auth0/auth0-react';
import { useEffect, useState } from 'react';

import { ApiError, fetchMe, useApi, type Me } from './api';
import { TaskList } from './components/TaskList';

function Landing() {
  const { loginWithRedirect } = useAuth0();

  return (
    <main>
      <h1>Taskly</h1>
      <button onClick={() => loginWithRedirect()}>Log in</button>
    </main>
  );
}

function Workspace() {
  const { user, logout } = useAuth0();
  const api = useApi();
  const [me, setMe] = useState<Me | null>(null);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    fetchMe(api)
      .then((result) => {
        setMe(result);
        setError(null);
      })
      .catch((e) => {
        setError(
          e instanceof ApiError && e.status === 401
            ? 'API rejected your token. Log out and log in again.'
            : String(e),
        );
      });
  }, [api]);

  return (
    <>
      <header>
        <h1>Taskly</h1>
        <span>{user?.email ?? user?.name}</span>
        <button onClick={() => logout({ logoutParams: { returnTo: window.location.origin } })}>
          Log out
        </button>
      </header>

      {error && <p role="alert">{error}</p>}

      {me && (
        <main>
          <TaskList me={me} />
        </main>
      )}
    </>
  );
}

export default function App() {
  const { isLoading, isAuthenticated, error } = useAuth0();

  if (isLoading) return <p>Loading…</p>;
  if (error)     return <p>{error.message}</p>;

  return isAuthenticated ? <Workspace /> : <Landing />;
}
