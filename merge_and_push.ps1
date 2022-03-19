param (
    [Paramter(Mandatory = $true)]
    [string] $CurrentBranch
)

$CheckoutMainCommand = "git checkout main"
$MergeWithBranchCommand = "git merge $CurrenBranch --no-ff"
$PushToMainCommand = "git push"
$ReturnToBranchCommand = "git checkout $CurrentBranch"


if (-not($CurrentBranch)) {
    Throw "Current branch must be specified"
}

if ($CurrentBranch.ToLower -eq "main") {
    Invoke-Expression "git push"
    return
}

if ($CurrentBranch -eq ".") {
    $CurrentBranch = &git rev-parse --abbrev-ref HEAD
}

Invoke-Expression $CheckoutMainCommand

Invoke-Expression $MergeWithBranchCommand

Invoke-Expression $PushToMainCommand

Invoke-Expression $ReturnToBranchCommand




