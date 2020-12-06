#include <bits/stdc++.h>
using namespace std;

void solve()
{
    int N, M;
    cin >> N >> M;
    vector<vector<int>> A(N);

    for (int i = 0; i < N; ++i) {
        A[i] = vector<int>(M);
        for (int j = 0; j < M; ++j) {
            cin >> A[i][j];
        }
    }

    int prevMax = 0, currMax = 0, max = 0;
    for (int i = 0; i < N; i++) {
        prevMax = 0;
        for (int j = 0; j < M; j++) {
            if (i > 0) {
                currMax = (j < M - 1) ? std::max(A[i-1][j], A[i-1][j+1]) : A[i-1][j];
                max = std::max(prevMax, currMax);
            }
            if (A[i][j] > max) {
                cout << "1";
            } else {
                A[i][j] = max;
                cout << "0";
            }
            prevMax = currMax;
        }
        cout << endl;
    }
}

int main(int argc, char const *argv[])
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int T = 0;
    cin >> T;
    for (auto x = 1; x <= T; x++)
    {
        solve();
    }

    return 0;
}